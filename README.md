#Project Name
Sign-up-service
#Description
This is a micro service programmed in Java with the help of Spring. Which represents a REST service for registration
and user log in to a system.
#Installation
Required: Java 11 version and Gradle 7.4 version
git clone 'https://github.com/lgcid14/sign-up-service.git'
run 'gradle clean build' command
#Test
Run project as Spring Boot application, then send request from Postman 
to 'http://localhost:8080/signup-service/sign-up' to sign up and 'http://localhost:8080/signup-service/login' to log in