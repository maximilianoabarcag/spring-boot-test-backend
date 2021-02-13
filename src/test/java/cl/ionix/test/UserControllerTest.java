package cl.ionix.test;

import java.security.GeneralSecurityException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import cl.ionix.test.controller.UserController;
import cl.ionix.test.exception.DuplicatedEntryException;
import cl.ionix.test.exception.NoContentException;
import cl.ionix.test.model.UserRequest;
import cl.ionix.test.model.UserResponse;
import cl.ionix.test.service.UserService;

@RunWith(SpringRunner.class)
@Sql("/datos.sql")
@SpringBootTest(properties = { "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect" })
@AutoConfigureTestDatabase
public class UserControllerTest {
	
	@Mock
	private UserService userService;
	
	@Autowired
	private UserController userController;
	
	@Test		
	public void UserControllerTest_List_OK() throws GeneralSecurityException, NoContentException {			
		ResponseEntity<List<UserResponse>> httpResponse = userController.getAllUsers();
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);		
	}	
	
	@Test		
	public void UserControllerTest_Create_User_OK() throws GeneralSecurityException, DuplicatedEntryException {			
		UserRequest userRequest = new UserRequest();
		userRequest.setEmail("test1@test.com");
		userRequest.setName("nombre de prueba");
		userRequest.setPhone("12312312");
		userRequest.setUserName("mxa135");
		ResponseEntity<UserResponse> httpResponse = userController.createUser(userRequest);	
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.CREATED);		
	}

}