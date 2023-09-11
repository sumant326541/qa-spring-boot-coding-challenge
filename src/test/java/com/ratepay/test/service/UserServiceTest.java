package com.ratepay.test.service;

import com.ratepay.test.exception.UnknownUserException;
import com.ratepay.test.model.User;
import com.ratepay.test.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Sumant
 */
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSaveUser() {
		//Create new user object
		String username = "newuser";
		String password = "Test@123";
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);

		// Action
		userService.save(newUser);

		// Assertion
		verify(userRepository, times(1)).save(newUser);
	}

	@Test
	public void testFindByUsername_UserFound() {
		//Create new user object
		String username = "existinguser";
		String password = "ExistingPassword";
		User existingUser = new User();
		existingUser.setUsername(username);
		existingUser.setPassword(password);
		when(userRepository.findByUsername(username)).thenReturn(Optional.of(existingUser));

		// Action
		User foundUser = userService.findByUsername(username);

		// Assertion
		assertNotNull(foundUser);
		assertEquals(username, foundUser.getUsername());
	}

	@Test
	public void testFindByUsername_UserNotFound() {
		//Create new user object
		String username = "nonexistentuser";
		when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

		// Action and Assertion
		assertThrows(UnknownUserException.class, () -> userService.findByUsername(username));
	}

	@Test
	public void testExists_UserExists() {
		//Create new user object
		String username = "existinguser";
		String password = "TestPassword";
		User existingUser = new User();
		existingUser.setUsername(username);
		existingUser.setPassword(password);
		when(userRepository.findByUsername(username)).thenReturn(Optional.of(existingUser));

		// Action
		boolean userExists = userService.exists(existingUser);

		// Assertion
		assertTrue(userExists);
	}

	@Test
	public void testExists_UserDoesNotExist() {
		//Create new user object
		String username = "nonexistentuser";
		String password = "TestPassword";
		User nonexistingUser = new User();
		nonexistingUser.setUsername(username);
		nonexistingUser.setPassword(password);
		when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

		// Action
		boolean userExists = userService.exists(nonexistingUser);

		// Assertion
		assertFalse(userExists);
	}
}
