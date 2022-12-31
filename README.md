# Event-Scheduler-Application
This is a Spring Boot application that provides a REST API for scheduling events. The API is secured using JSON Web Tokens (JWT). Swagger UI is also provided for easy testing and documentation of the API.

## Prerequisites
- Java 17 or above
- Maven

# Building and Running the Application
### Clone the repository and navigate to the root directory of the project:
- git clone https://github.com/Sirajuddin135/Event-Scheduler-Application.git
<!-- - cd Event-Scheduler-Application

# Build the project using Maven:
- mvn clean install -->

# Run the application:
<!-- - java -jar target/event-scheduler-0.0.1-SNAPSHOT.jar -->
- The application will be available at http://localhost:8080.

# API Documentation
- The API documentation is available at http://localhost:8080/swagger-ui.html.

# Security
- The API is secured using JSON Web Tokens (JWT). To access the API, you will need to obtain a JWT by authenticating with the /login endpoint. The JWT should then be passed in the Authorization header of each API request.

### Example:
- Authorization: <your_jwt>

# Thank You
