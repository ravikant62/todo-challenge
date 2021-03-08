# Deloitte

# How to build/run it

H2 is used as embedded database      
This is a maven project built with maven 3.6.1,Java version: 1.8.0_211  .        
Project can be built using command line : mvn clean install.    
Server runs on 8080 port number


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
