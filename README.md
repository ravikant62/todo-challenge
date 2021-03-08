# ProgressSoft

# How to build/run it

Setup MySQL on your local environment. Create schema "deals .       
This is a maven project built with maven 3.5.0,Java version: 1.8.0_131  .        
Project can be built using command line : mvn clean install  .   
To run the application using command line : mvn spring-boot:run .  
Application is accessible at :  
1. Upload page - http://localhost:8080/upload
2. Search page - http://localhost:8080/search  ( Caching implemented )


# Technology Used

Java 8 , Spring boot 1.5.10 , Maven 3.5.0 , Spring boot JPA  , Spring boot thymeleaf , Spring Caching , MySQL           
Operating System : macOS Sierra 10.12.3

# Problem Statement

Clustered Data Warehouse

Suppose you are part of a scrum team developing data warehouse for Bloomberg to analyze FX deals. One of customer stories is to import deals details from files into DB. The requested performance is to be able to import the file containing 100,000 records in less than 5 seconds.


Request logic as following :

File format is CSV contains the following fields (Deal Unique Id, From Currency ISO Code "Ordering Currency", To Currency ISO Code, Deal timestamp, Deal Amount in ordering currency).
Validate row structure.(e.g: Missing fields, Type format..etc. We do not expect you to cover all possible cases but we'll look to how you'll implement validations)
Valid rows should be stored in table/document, with reference to source file name .
Invalid rows should be stored into another table/document, with reference to source file name.
The DB contains another table to maintain accumulative count of deals per Ordering Currency "Columns : Currency ISO Code, CountOfDeals ", so upon completion of importing process the system should increase count of deals per currency.
System should not import same file twice.
No rollback allowed, what every rows imported should be saved in DB.

Technical Specs :

Access to DB should be through JPA.
For DB type, you can select between (MySql or MongoDB)
Provide a web interface for uploading files and inquire about results "using filename" following web applications 3 tier architecture. Spring Batch is not allowed.
Provide a web interface for inquire about results "using filename" .·
We expect you to generate sample data set to use it during development
We expect the system to display a summary report for each file after process is finished; summary may contain process duration, number of imported deals and number of invalid records.
We expect you to generate sample data set to use it during development. 

Deliverables should be ready to work including :

Workable deployment including sample file.
Deployment steps including sample data of 100K records, contains all invalid/valid records that the system handles.
Maven or Gradle project is required for full source code.
Proper error/exception handling.
Proper Logging.
It is essential to follow TDD and include unit testing of your code and provide it as part of the assignment, noting that this will be one of the major assessment points, minimum code coverage should be 70%.
It is preferred to deliver via github or bitbucket repository.
Provide deployment as a Vagrant or Docker is a plus.