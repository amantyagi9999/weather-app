# Weather App

This is a Spring Boot project built using Java and Maven to provide weather update for "Melbourne" city using "WeatherStack" & "OpenWeatherMap" API.


## Getting Started

### Prerequisites

* Java 17 or later
* Maven 3.6 or later
* Spring Boot 3.3.7 or later

### Building the Project

1. Clone the project repository to your local machine using Git.
2. Import the project in any IDE via locating `pom.xml` file.
3. Run `mvn clean package` to build the project.

### Running the Project

1. Run `mvn spring-boot:run` to start the application or you can use `WeatherApplication` class to run directly in IDE.'

## Environment Variables

You can set environment variables in `application.properties` file, for example, you can set the `SERVER_PORT`, `LOG_LEVEL`,`API_KEYS` variable to change the config as per your requirement.

## Troubleshooting

* Check the Console logs for errors.
* If you're using an IDE, make sure to configure the project correctly and try running the application from the IDE.

## What can be improved if i had additional time on task
1. I could have implemented `SWAGGER`/`OPENAPI` for API documentation.
2. I could have implemented `Mockito` for unit Testing.
3. I could have implemented `Spring Security` to secure the API based on user authentication/authorization.
4. Currently default logging is implemented provided by spring. I can implement `SLF4J`/`LOG4J2`/`LOG4J` for logging using `Spring AOP`.
5. Current default caching is implemented provided by spring. I can enhance caching by using `Redis`.
6. I can implement Input validation using `Spring Validation`.
7. Currently using `REST TEMPLATE` to call the third party API. I could have implemented this using `Feign Client`.
8. 
8. 