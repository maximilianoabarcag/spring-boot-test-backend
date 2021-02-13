package cl.ionix.test.controller;

import java.security.GeneralSecurityException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ionix.test.domain.EndPoint;
import cl.ionix.test.exception.DuplicatedEntryException;
import cl.ionix.test.exception.NoContentException;
import cl.ionix.test.exception.NotFoundException;
import cl.ionix.test.model.UserRequest;
import cl.ionix.test.model.UserResponse;
import cl.ionix.test.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "User")
@RestController
@RequestMapping(EndPoint.VERSION_1 + "/user")
public class UserController {

	@Autowired
	UserService userService;

	@ApiOperation(value = "create user", notes = "create a new user")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 406, message = "Not Acceptable"),
			@ApiResponse(code = 422, message = "Unprocessable Entity"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest)
			throws GeneralSecurityException, DuplicatedEntryException {
		return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.CREATED);
	}

	@ApiOperation(value = "get users list", notes = "list all stored users")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 204, message = "No Content"), @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 406, message = "Not Acceptable"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserResponse>> getAllUsers() throws GeneralSecurityException, NoContentException {
		return new ResponseEntity<List<UserResponse>>(userService.getAllUsers(), HttpStatus.OK);
	}

	@ApiOperation(value = "get user by email", notes = "get user by given email address")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> getUserByEmail(@Valid @PathVariable String email)
			throws GeneralSecurityException, NotFoundException {
		return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
	}
}
