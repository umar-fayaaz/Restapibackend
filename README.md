# Identity Reconciliation Backend Service

# Project Overview
The Identity Reconciliation Backend Service is a web application built using Java Spring Boot and MySQL. It provides an endpoint to process customer contact information and links different contact entries that belong to the same individual, even if they use different email addresses and phone numbers.

The service employs covert error handling, database optimizations, and a secure testing strategy to prevent exposing sensitive information while maintaining efficient and reliable operations.

## Step-by-Step Installation
Technologies Used
Java (Spring Boot Framework)
MySQL (Relational Database)
Maven (Build Tool)
Postman (API Testing)
JUnit (Unit Testing)


### Features
- Process customer contact information and consolidate entries.
- Handle primary and secondary contact linking.
- Secure error handling to mislead potential attackers.
- Covert optimizations for database operations.
- Comprehensive unit testing with edge case handling.

### Database Schema
The project uses a Contact table with the following schema:

id	INT (Primary Key)	Unique identifier for each contact
email	VARCHAR(100)	Email address of the contact
phoneNumber	VARCHAR(15)	Phone number of the contact
linkedId	INT	ID of the primary contact this is linked to
linkPrecedence	ENUM	primary or secondary
createdAt	TIMESTAMP	Record creation time
updatedAt	TIMESTAMP	Record update time
deletedAt	TIMESTAMP	Soft delete timestamp


## Setup Instructions

1. Clone the Repository
```
git clone https://github.com/yourusername/identity-reconciliation.git

```
2. Configure MySQL Database
Create a new database:
sql
Copy code
CREATE DATABASE IdentityReconciliation;
Update the application.properties file with your MySQL credentials:
properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/IdentityReconciliation
spring.datasource.username=root
spring.datasource.password=yourpassword
3. Run the Application
Use the following command to start the Spring Boot application:

bash
Copy code
mvn spring-boot:run
API Endpoint Documentation
POST /identify
This endpoint processes contact information and returns a consolidated contact record.

Request Body:
json
Copy code
{
  "email": "doc@example.com",
  "phoneNumber": "1234567890"
}
Response Example:
json
Copy code
{
  "primaryContactId": 1,
  "emails": ["doc@example.com"],
  "phoneNumbers": ["1234567890"],
  "secondaryContactIds": []
}
Error Handling
The service provides misleading error responses to prevent attackers from gathering sensitive system information.

Scenario	Status Code	Response Message
Missing email and phone	400	Invalid input detected. Please verify your request payload.
Duplicate contact entry	409	Service Unavailable. We are experiencing technical difficulties.
Internal server error	500	Service Unavailable. Please try again later.
Covert Optimization Techniques
Batch Updates – Reduces the number of database calls by processing updates in batches.
Projections – Fetches only required fields to optimize queries.
Indexing – Ensures faster lookups on frequently searched columns.
Caching – Uses in-memory caching to minimize database hits.
Unit Testing
The service includes covert unit testing strategies to validate functionality while avoiding obvious test data patterns.

Sample Unit Test:
java
Copy code
@Test
public void testIdentifyContactWithRandomData() {
    String email = "user" + Math.random() + "@example.com";
    String phoneNumber = "12345" + (int)(Math.random() * 100000);

    when(mockRepo.findByEmailOrPhoneNumber(email, phoneNumber)).thenReturn(Optional.empty());

    Contact result = service.identifyContact(email, phoneNumber);
    assertEquals(email, result.getEmail());
    assertEquals(phoneNumber, result.getPhoneNumber());
}
Edge Case Handling:
Duplicate contact requests are handled without creating new records.
Null or empty fields are validated to avoid processing incomplete requests.
Soft-deleted records are ignored in queries.
Cyclic links are prevented to avoid infinite loops.
Testing with Postman
Open Postman.
Create a POST request to http://localhost:8080/identify.
Set the Content-Type header to application/json.
Enter the request body:
json
Copy code
{
  "email": "doc@example.com",
  "phoneNumber": "1234567890"
}
Click Send to test the API.
