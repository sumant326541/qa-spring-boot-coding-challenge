package com.ratepay.test.validator;
import com.ratepay.test.exception.ValidationError;
import com.ratepay.test.model.User;
import com.ratepay.test.service.UserService;
import com.ratepay.test.validator.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * @author Sumant
 */

public class UserValidatorTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserValidator userValidator;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testValidate_ValidUser() {
		//Create new user object
		String username = "validuser";
		String password = "ValidPassword";
		User validUser = new User();
		validUser.setUsername(username);
		validUser.setPassword(password);


		// Mock the behavior of the UserService.exists method
		when(userService.exists(validUser)).thenReturn(false);

		// Action & Assertion
		assertDoesNotThrow(() -> userValidator.validate(validUser));
	}

	@Test
	public void testValidate_UserAlreadyRegistered() {
		//Create new user object
		String username = "existinguser";
		String password = "ExistingPassword";
		User existingUser = new User();
		existingUser.setUsername(username);
		existingUser.setPassword(password);

		// Mock the behavior of the UserService.exists method
		when(userService.exists(existingUser)).thenReturn(true);

		// Action & Assertion
		ValidationError validationError = assertThrows(ValidationError.class, () -> userValidator.validate(existingUser));
		assertEquals("User already registered", validationError.getMessage());
	}

	@Test
	public void testValidate_InvalidUsername() {
		//Create new user object
		String username = "short";
		String password = "ValidPassword";
		User invalidUsernameUser = new User();
		invalidUsernameUser.setUsername(username);
		invalidUsernameUser.setPassword(password);

		// Mock the behavior of the UserService.exists method
		when(userService.exists(invalidUsernameUser)).thenReturn(false);

		// Action & Assertion
		ValidationError validationError = assertThrows(ValidationError.class, () -> userValidator.validate(invalidUsernameUser));
		assertEquals("username is not valid", validationError.getMessage());
	}

	@Test
	public void testValidate_InsecurePassword() {
		//Create new user object
		String username = "validuser";
		String password = "short";
		User insecurePasswordUser = new User();
		insecurePasswordUser.setUsername(username);
		insecurePasswordUser.setPassword(password);

		// Mock the behavior of the UserService.exists method
		when(userService.exists(insecurePasswordUser)).thenReturn(false);

		// Action & Assertion
		ValidationError validationError = assertThrows(ValidationError.class, () -> userValidator.validate(insecurePasswordUser));
		assertEquals("password is not secure", validationError.getMessage());
	}
}

