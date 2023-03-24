# Dental Clinic Appointment Booking System (APIRest)

This project aims to implement a system for managing appointment bookings for a dental clinic. The system must fulfill the following requirements:

## Requirements

- **Dentist management**: List, add, modify, and delete dentists. Record their last name, first name, and registration number.
- **Patient management**: List, add, modify, and delete patients. Store their first name, last name, address, ID, and registration date.
- **Appointment scheduling**: Assign an appointment to a patient with a specific dentist at a specific date and time.
- **Login**: Validate access to the system through a login with a username and password. Any logged-in user (ROLE_USER) must be able to book an appointment, but only those with an admin role (ROLE_ADMIN) can manage dentists and patients. A user can have only one role, and the roles will be entered directly into the database.

## Technical Requirements

The application must be developed in layers:

### Business entities layer
These are the Java classes representing our business objects modeled through the object-oriented paradigm.

### Data access layer (Repository)
These are the classes responsible for accessing the database.

### Data layer (database)
This is the database of our system, modeled through an entity-relationship model. We will use the H2 database for its convenience.

### Business layer
These are the service classes responsible for decoupling data access from the view.

### Presentation layer
These are the web pages that need to be developed using the Spring Boot MVC framework with controllers and one of these two options for the view: HTML+JavaScript or React.

It's essential to handle exceptions by logging any exception that may occur and performing unit tests to ensure the quality of the developments.

This project is developed using the Spring framework and related technologies.

## H2 Database Needed!!

Remember this configuration will need the H2 database installed in your system:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create
