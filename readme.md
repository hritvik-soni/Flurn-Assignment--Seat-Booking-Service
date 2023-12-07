# Seat Booking System

## Overview

This repository contains the implementation of a Seat Booking System. The system manages different seat classes with associated pricing based on the booking history. During the booking process, the pricing is determined by the occupancy rate of the chosen seat class.

## Pricing Rules

- Less than 40% of seats booked: Use `min_price`. If `min_price` is not available, use `normal_price`.
- 40% - 60% of seats booked: Use `normal_price`. If `normal_price` is not available, use `max_price`.
- More than 60% of seats booked: Use `max_price`. If `max_price` is not available, use `normal_price`.

## APIs

### Get All Seats

**Endpoint**
```bash
GET /seats
```
#### Description
Returns all seats ordered by seat class and includes a boolean indicating whether each seat is booked.


### Get Seat Pricing

**Endpoint**
```bash
GET /seats/{id}
```
#### Description
Returns seat details along with pricing based on the bookings previously made for that seat class.

#### Pricing Logic

- Less than 40% booked: min_price (or normal_price if min_price is not available)
- 40% - 60% booked: normal_price (or max_price if normal_price is not available)
- More than 60% booked: max_price (or normal_price if max_price is not available)
### Create Booking

**Endpoint**
```bash
POST /booking
```
Request Body

```json
{
"seatIds": [1, 2, 3],
"sserName": "John Doe",
"phoneNumber": "1234567890", 
  "userEmail": "emailaddress"
}
```
#### Description
Creates a booking for the selected seats. Returns a booking ID and the total amount of the booking upon success.


### Retrieve Booking

**Endpoint**
```bash
GET /bookings?userIdentifier=<email or phone number>
```

#### Description
Returns all bookings created by the user identified by either email or phone number. Returns an error if no user identifier is provided.

### Tech Stack
- Java 
- Spring Boot
- MySQL
- Hibernate 
- JPA
- AWS (EC2,RDS)

### Deployment
The application is hosted on an Amazon EC2 instance.

### Getting Started
To run the application locally, follow these steps:

- Clone the repository.
- Configure the database connection in the application.properties file.
- Build and run the application using Maven or your preferred IDE.