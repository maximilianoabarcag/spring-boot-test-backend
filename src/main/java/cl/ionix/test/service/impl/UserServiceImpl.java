package cl.ionix.test.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ionix.test.entity.User;
import cl.ionix.test.exception.DuplicatedEntryException;
import cl.ionix.test.exception.NoContentException;
import cl.ionix.test.exception.NotFoundException;
import cl.ionix.test.model.UserRequest;
import cl.ionix.test.model.UserResponse;
import cl.ionix.test.repository.UserRepository;
import cl.ionix.test.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User findUserByUserName(String userName) throws DuplicatedEntryException {
		return userRepository.findByUserName(userName);
	}
	
	@Override
	public User findUserByEmail(String email) throws DuplicatedEntryException {
		return userRepository.findByEmail(email);
	}

	@Override
	public UserResponse createUser(UserRequest userRequest) throws DuplicatedEntryException {

		if (findUserByUserName(userRequest.getUserName()) != null) {
			throw new DuplicatedEntryException("The User Name '" + userRequest.getUserName() + "' already exists!");
		}

		if (findUserByEmail(userRequest.getEmail()) != null) {
			throw new DuplicatedEntryException("The Email '" + userRequest.getEmail() + "' is already in use!");
		}

		User user = new User();
		user.setName(userRequest.getName());
		user.setUserName(userRequest.getUserName());
		user.setEmail(userRequest.getEmail());
		user.setPhone(userRequest.getPhone());

		User createdUser = userRepository.save(user);

		UserResponse userResponse = new UserResponse();
		userResponse.setUserId(createdUser.getUserId());
		userResponse.setUserName(user.getUserName());

		return userResponse;
	}

	@Override
	public List<UserResponse> getAllUsers() throws NoContentException {
		List<User> userList = userRepository.findAll();
		
		List<UserResponse> listUserResponse = new ArrayList<UserResponse>();

		for (User user : userList) {
			UserResponse userResponse = new UserResponse();
			userResponse.setUserId(user.getUserId());
			userResponse.setName(user.getName());
			userResponse.setUserName(user.getUserName());
			userResponse.setEmail(user.getEmail());
			userResponse.setPhone(user.getPhone());

			listUserResponse.add(userResponse);
		}
		
		if(listUserResponse.isEmpty()) {
			throw new NoContentException("No results");
		}
		
		return listUserResponse;
	}

	@Override
	public UserResponse getUserByEmail(String email) throws NotFoundException {
		User user = userRepository.findByEmail(email);
		
		if(user == null) {
			throw new NotFoundException("No user found with email " + email);
		}
		
		UserResponse userResponse = new UserResponse();
		userResponse.setUserId(user.getUserId());
		userResponse.setName(user.getName());
		userResponse.setEmail(user.getEmail());
		userResponse.setPhone(user.getPhone());
		
		return userResponse;
	}

}
