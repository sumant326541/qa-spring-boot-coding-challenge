# QA-Engineer Spring Boot Coding Challenge

## Task
The goal is to create one positive and one negative test case with the Spring Boot framework for the following testing-layer of the [microservice test pyramid](https://martinfowler.com/articles/microservice-testing/#conclusion-test-pyramid):
* Unit test
* Integration test
* Component test
* End-To-End test

Please create:
* a git branch with the name firstname_lastname
* a test class for each layer containing the tests 

Feel free to research the internet and add dependencies to the maven project.

## Application
The application is build with Spring boot and offers two endpoints:

### Registration of user
#### Request
POST /registration
Body:
{
"username": "",
"password": ""
}

#### Response

201 User was created

422 Validation Error

400 Syntactically invalid request

Username must be at least 6 digits long and a maximum of 32 digits
Password must be at least 8 digits long and a maximum of 32 digits


### Retrieval of user information
#### Request
GET /users/{username}

#### Response

200 User found

404 User not found

Body:
{
"id": "",
"username": "",
"password": ""
}

## Running the application
The application including a running database can be started by executing inside the project folder mvn spring-boot:run or by running the java class SpringBootCodingChallenge.java