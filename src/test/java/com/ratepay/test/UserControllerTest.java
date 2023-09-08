package com.ratepay.test;

import com.ratepay.test.model.User;
import com.ratepay.test.service.UserService;
import com.ratepay.test.validator.UserValidator;
import com.ratepay.test.web.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Sumant
 */

public class UserControllerTest {

	@Mock
	private UserService userService;

	@Mock
	private UserValidator userValidator;

	@InjectMocks
	private UserController userController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetUserByName() {
		//Create new user object
		String username = "testuser";
		String password = "Test@123";
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		when(userService.findByUsername(username)).thenReturn(user);

		// Action
		ResponseEntity<?> responseEntity = userController.getUserByName(username);

		// Assertion
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(user, responseEntity.getBody());
	}

	@Test
	public void testCreateNewUser() {
		//Create new user object
		String username = "testnewuser";
		String password = "Test@123";
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);

		// Action
		ResponseEntity<?> responseEntity = userController.createNewUser(newUser);

		// Assertion
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		verify(userValidator, times(1)).validate(newUser);
		verify(userService, times(1)).save(newUser);
	}
}

