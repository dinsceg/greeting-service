# Greeting service

This repository contains implementation of the Greeting service.


## Building

You can build the application by invoking

```
$ mvn clean package
```

Or, to run all tests including integration tests:
```
$ mvn clean verify
```

## Running

The application can be executed locally using Maven or just as a regular Java application. With Maven you can use the Spring Boot Maven plugin:

```
$ mvn spring-boot:run
```

To run the application as a java application with the same Spring profile, you can

```
$ java -jar greeting-service-1.0.0-SNAPSHOT.jar 
```


## Exposed endpoints

The application currently exposes its API specification in <http://localhost:5000/swagger-ui.html> or <http://localhost:5000/v2/api-docs> if you prefer the API JSON model.
