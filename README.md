# Identity Reconciliation Backend Service

# Project Overview
The Identity Reconciliation Backend Service is a web application built using Java Spring Boot and MySQL. It provides an endpoint to process customer contact information and links different contact entries that belong to the same individual, even if they use different email addresses and phone numbers.

The service employs covert error handling, database optimizations, and a secure testing strategy to prevent exposing sensitive information while maintaining efficient and reliable operations.

## Step-by-Step Installation
- Technologies Used
- Java (Spring Boot Framework)
- MySQL (Relational Database)
- Maven (Build Tool)
- Postman (API Testing)
- JUnit (Unit Testing)

### Features
- Process customer contact information and consolidate entries.
- Handle primary and secondary contact linking.
- Secure error handling to mislead potential attackers.
- Covert optimizations for database operations.
- Comprehensive unit testing with edge case handling.

### Database Schema
The project uses a Contact table with the following schema:

- id	INT (Primary Key)	Unique identifier for each contact
- email	VARCHAR(100)	Email address of the contact
- phoneNumber	VARCHAR(15)	Phone number of the contact
- linkedId	INT	ID of the primary contact this is linked to
- linkPrecedence	ENUM	primary or secondary
- createdAt	TIMESTAMP	Record creation time
- updatedAt	TIMESTAMP	Record update time
- deletedAt	TIMESTAMP	Soft delete timestamp


## Setup Instructions

1. Clone the Repository
```
git clone https://github.com/umar-fayaaz/Restapibackend.git
```
2. Configure MySQL Database
Update the application.properties file with your MySQL credentials
```
spring.application.name=backend
spring.datasource.url=jdbc:mysql://localhost:3306/userlinkin?createDatabaseIfNotExist=true
spring.datasource.username=Your username
spring.datasource.password=Your password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=1234
```
3. Run the Application
Use the following command to start the Spring Boot application:

``` 
mvn spring-boot:run
```
4. API Endpoint Documentation
POST api/identify
This endpoint processes contact information and returns a consolidated contact record.

Request Body:
json
```
{
  "email": "doc@example.com",
  "phoneNumber": "1234567890"
}
```
Response Example:
json
```
{
  "emails": ["doc@example.com"],
  "LinkedPrecedence": "PRIMARY",
  "phoneNumbers": ["1234567890"],
  "primaryContactId": 1
}
```
5.Error Handling
The service provides misleading error responses to prevent attackers from gathering sensitive system information.

Scenario
- Missing email and phone	400	Invalid input detected. Please verify your request payload.
- Internal server error	500	Service Unavailable. Please try again later.

