package cl.ionix.test.service;

import java.util.List;

import cl.ionix.test.entity.User;
import cl.ionix.test.exception.DuplicatedEntryException;
import cl.ionix.test.exception.NoContentException;
import cl.ionix.test.exception.NotFoundException;
import cl.ionix.test.model.UserRequest;
import cl.ionix.test.model.UserResponse;

public interface UserService {
	public User findUserByUserName(String userName) throws DuplicatedEntryException;
	public UserResponse createUser(UserRequest taskRequest) throws DuplicatedEntryException;
	public List<UserResponse> getAllUsers() throws NoContentException;
	public UserResponse getUserByEmail(String email) throws NotFoundException;
	public User findUserByEmail(String email) throws DuplicatedEntryException;
}
