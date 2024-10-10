## Overview

A RESTful API service built using Spring Boot to manage an online Hotel Room Booking System with MySQL as the database for persisting data.

## Features

- **Authentication and Authorization**:
  - Implemented using JWT tokens with three roles: CUSTOMER, HOTEL MANAGER and ADMIN.
- **User Management**:
  - Registration: User's register with email, password (encrypted using BCrypt), first name, last name and a role (defaulted to "CUSTOMER" if not specified).
  - Login: User's log in using email and password to receive a JWT token.
- **Hotel Management**:
  - Hotel Details: Store and manage hotel details like name, location, description, and room availability.
  - Public Access: Anyone can browse all available hotels.
  - Admin Access: Admins can create and delete hotels.
  - Hotel Manager Access: Managers can update hotel details.
- **Booking Management**:
  - Book a Room: Customers can book available rooms.
  - Cancel a Booking: Hotel managers can cancel bookings.

## Getting Started

### Prerequisites

- Java 17 or higher
- MySQL

### Installation

1. **Clone the repository:**

    ```bash
    git clone https://github.com/pgprajwal/StayEase.git
    ```

2. **Navigate to the project directory:**

    ```bash
    cd StayEase
    ```
    
### Configuration

1. Open the `application.properties` file located in `src/main/resources` directory.

2. Configure the following settings:
   1. **MySQL database connection settings:**

      ```bash
      spring.datasource.url = jdbc:mysql://${mysql.host}:${mysql.port}/${mysql.database}
      spring.datasource.username = ${mysql.username}
      spring.datasource.password = ${mysql.password}
      ```
      Replace `${mysql.host}`, `${mysql.port}`, `${mysql.database}`, `${mysql.username}` and `${mysql.password}` with your MySQL Host, Port, Database, Username and Password respectively.
   
   2. **JWT Security Key:**
      
      ```bash
      spring.application.security.jwt.secret.key = ${jwt-secret-key}
      ```

      Add the above configuration and replace `${jwt-secret-key}` with your actual 256-bit JWT secret key.

3. Save the changes to the `application.properties` file.

### Running the Application

* **Using Gradle:**

    ```bash
    ./gradlew bootRun
    ```
* **Using a Generated JAR File:**

  - Build the JAR File: 

  ```bash
  ./gradlew build
  ```
  
  - Run the generated JAR file:

  ```bash
  java -jar build/libs/stayEase-0.0.1-SNAPSHOT.jar
  ```

## API Endpoints

You can find and test the API endpoints using Swagger by navigating to http://localhost:8080/swagger-ui.html after running the application.
