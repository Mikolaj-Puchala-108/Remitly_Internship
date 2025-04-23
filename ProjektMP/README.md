# SWIFT Code Management API

A Spring Boot-based application that allows managing SWIFT codes for banks and their branches. It enables retrieving, adding, and removing bank data stored in a MySQL database. The project is fully containerized using Docker and Docker Compose.

## Table of Contents

- [Project Description](#project-description)
- [System Requirements](#system-requirements)
- [Installation and Setup](#installation-and-setup)
- [REST API Structure](#rest-api-structure)
- [Technologies Used](#technologies-used)
- [Author](#author)

## Project Description

The project allows managing SWIFT codes for banks and their branches. Features include:
- Retrieving bank data based on SWIFT code.
- Retrieving a list of banks based on the country code (ISO2).
- Adding new records.
- Deleting existing records.

The application can be run locally using Docker containers. Data is saved in a persistent database.

## System Requirements

To run the application, you will need:

- Java 17
- Maven 3.8+
- Docker
- Docker Compose
- Git

## Installation and Setup

1. **Clone the repository:**
bash
   git clone https://github.com/Mikolaj-Puchala-108/Remitly_Internship.git
   cd Remitly_Internship\ProjektMP

2. **Build the application:**
bash
   docker-compose up --build

The application will be available at:
   http://localhost:8080/[...]

## REST API Structure

GET /v1/swift-codes/{swiftCode}
   Retrieves details of a bank or branch based on the SWIFT code.

GET /v1/swift-codes/country/{countryISO2}
   Retrieves all banks in the given country.

POST /v1/swift-codes
   Adds a new SWIFT record. Data in JSON format.

DELETE /v1/swift-codes/{swiftCode}
   Deletes a record from the database.

## Technologies Used in the Project

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL 8.4
- Hibernate
- Docker, Docker Compose
- Maven

## Author

Mikołaj Puchała
Project for recruitment purposes and to improve backend and containerization technologies.
