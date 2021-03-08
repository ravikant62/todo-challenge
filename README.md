# Deloitte

# How to build/run it

H2 is used as embedded database      
This is a maven project built with maven 3.6.1,Java version: 1.8.0_211  .        
Project can be built using command line : mvn clean install.
To run the application using command line : mvn spring-boot:run
Server runs on 8080 port number.
3 credentials can be used to login to the application (login, password)
1. user1, pass
2. user2, pass
3. user3, pass


# Technology Used

Java 8 , Spring boot 2.2.7, Maven 3.6.1 , Spring boot JPA, Spring security, JWT (authorization)           
Operating System : macOS Sierra 10.12.3

# Problem Statement

You are required to build a simple online TODO list with a web interface that can be used in
all popular web browsers. The application has to Support multiple users, store necessary
data in a in-memory database and be built with J2EE technology (use of open-source
frameworks is also allowed)

# Minimal functionality
1. User can sign in using unique login and password securely (this can be hard coded
to a default user list, at list one user e.g. with username: test, password: pwd123)
2. User can view her/his task list
3. User can add/remove task
4. All changes can be persistent to allow view them in next sign in by the same user
5. Each task should display the date of last updates and description
6. User can check/uncheck any task on their list
7. Consider performance

# Project Structure
Screenshot 2021-03-08 at 16.23.30![image](https://user-images.githubusercontent.com/34513404/110349659-d0808f00-802a-11eb-995f-89dc526aefda.png)

# Controllers 
1. Auth Rest Api is responsible to login (http://localhost:8080/login)
2. TodoListController retrieves, creates, updates and delete a todolist (http://localhost:8080/lists)
3. TodoController retrieves, creates, updates and delete a todo item (http://localhost:8080/todos)

Spring Security is used for security implementation (WebSecurityConfig). JWT token (JwtResponse) is generated once the user credential is validated.
Cors is used to allow angular application to consume api response.
Custom PasswordEncoder (CustomPasswordEncoder) is implementated to avoid any encoding and decoding.
End to End testing is implemented using TestRestTemplate class from spring boot. 
I ran out of time to implement each unit test case, so instead implemented end to end testing to ensure coverage of the code and edge cases.

# Would like to implement
1. Caching
2. Expose endpoint for creating, updating, disabling users.


