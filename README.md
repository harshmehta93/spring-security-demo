# spring-security-demo
This project is a Spring-boot based user-microservice. This project is created to get you started with basic Spring-boot functionalities, basic Spring-security features and how you can secure your Spring-boot based microservice using Spring-security.

You should be able to understand following features after going through this project:
- How to create and use REST APIs using Spring-boot
- How to provide Spring-security based user login mechanism
- How to configure ROLE based authorization for each created REST API endpoints using Spring-security
- How to configure access-control mechanism for your application

This project demonstrates following functionalities:
- Users can register themselves
- Provides list of registered users
- Update user details
- Delete user registered user

Usecase:
- Now let's say we want to provide restrictions on update-user and delete-user functionalities, means user with only ADMIN access should be able to update or delete the particular user.
- So to implement this functionality, we have configured ROLE based authorization for update-user and delete-user REST endpoints, so user with only ADMIN role will be able to use these features.
- We have also configured Spring-security in such a way that if user tries to update or delete user and is not logged-in, then Spring-security will redirect user to login page, and after user logged-in successfully, if it is authorized user, then only operation will be performed successfully, otherwise user will be notified that "you don't have required access to perform this operation.".
