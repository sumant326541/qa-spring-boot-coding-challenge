package com.ratepay.test.E2ETest;


import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserRegistrationE2ETest {

	@Test
	public void testRegistrationWithValidData() {
		// Prepare a valid registration request
		String requestBody = "{\"username\": \"TestUser\", \"password\": \"Test@123\"}";

		// Send a POST request to the registration endpoint
		Response response = given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/registration");

		// Assert the response
		assertEquals(HttpStatus.CREATED.value(), response.getStatusCode()); // 201 CREATED

		testValidateRegisteredUser();
	}

	public void testValidateRegisteredUser() {
		String username = "TestUser";

		// Send a GET request to the /users/{username} endpoint
		Response response = given()
				.when()
				.get("/users/" + username);

		// Assert the response
		assertEquals(HttpStatus.OK.value(), response.getStatusCode()); // Assuming 200 ( User found)indicates success
	}

	@Test
	public void testValidateNotRegisteredUser() {
		String username = "sampleUser";

		// Send a GET request to the /users/{username} endpoint
		Response response = given()
				.when()
				.get("/users/" + username);

		// Assert the response
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode()); // Assuming 404 (User not found )indicates success
	}

	@Test
	public void testRegistrationWithInvalidUsername() {
		// Prepare a registration request with an username less then 6 digits long
		String requestBody = "{\"username\": \"12345\", \"password\": \"securepassword\"}";

		// Send a POST request to the registration endpoint
		Response response = given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/registration");

		// Assert the response
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), response.getStatusCode()); // 422 "Unprocessable Entity"
	}

	@Test
	public void testRegistrationWithInvalidPassword() {
		// Prepare a registration request with an less then 8 digits long password
		String requestBody = "{\"username\": \"validuser\", \"password\": \"1234567\"}";

		// Send a POST request to the registration endpoint
		Response response = given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/registration");

		// Assert the response
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), response.getStatusCode()); // 422 "Unprocessable Entity"

	}


}
