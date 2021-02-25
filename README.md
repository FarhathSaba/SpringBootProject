# SpringBootProject

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `de.codecentric.springbootsample.Application` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

# Project with crud operations and JWT token implemented
# Steps:
* 1.All your REST API's are authorized with JWT token
* 2.First get acess token from /api/getaccessToken and pass this token as authorization header to access all private REST endpoints.
* 3.Project has h2 database embedded and swagger UI implemented.
* 4.The web application is accessible via localhost:8080

# Swagger UI is accessible :
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

# H2Console is accessible :
http://localhost:8080/h2-console/
