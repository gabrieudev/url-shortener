# URL Shortener

![Java](https://img.shields.io/badge/Java-8%2B-orange) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-blue) [![LinkedIn](https://img.shields.io/badge/Connect%20on-LinkedIn-blue)](https://www.linkedin.com/in/gabrieudev) ![Spring Boot](https://img.shields.io/badge/Type-Challenge-purple) ![GPL License](https://img.shields.io/badge/License-GPL-blue)

This is my solution for the **URL Shortener** challenge, a REST API developed with Spring Boot. 

Please select your preferred language:

- [English](README.md)
- [Português (Brasil)](README.pt-br.md)

Check the [challenge](https://github.com/backend-br/desafios/blob/master/url-shortener/PROBLEM.md) for more information about the problem.

## Table of Contents

- Introduction
- Features
- Technologies
- Getting Started
- Configuration
- Endpoints
- Contributions
- Contact

## Introduction

The challenge consists of creating a REST API capable of receiving a URL, storing it in a database with an expiration date, and redirecting to the original URL when accessed.

## Features

- Create shortened URLs
- Access the original URL via the shortened URL

## Technologies

- ![Java](https://img.shields.io/badge/Java-8%2B-orange): Programming language.
- ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green): Framework used for building web applications.
- ![MongoDB](https://img.shields.io/badge/MongoDB-Database-gree): NoSQL database.

## Getting Started

Follow these steps to run the project on your machine:

1. Clone the repository: `git clone https://github.com/gabrieudev/url-shortener.git`
2. Navigate to the project directory: `cd <path>`
3. Update the database configurations in the `application.properties` file.
4. Build the project: `./mvnw clean install` (for Windows: `mvnw.cmd clean install`)
5. Run the application: `./mvnw spring-boot:run` (for Windows: `mvnw.cmd spring-boot:run`)

## Configuration

- Update the `application.properties` file with your MongoDB database information.
- Set how long a URL will remain in the database in `UrlService.java` by changing the LocalDateTime parameter for the `UrlModel` entity constructor in the `save` method (default is 1 hour).

## Endpoints

- `POST /shorten-url`: Shortens and saves a URL in the database.
- `GET /{id}`: Redirects to the original URL when accessing the shortened URL.

Access the complete documentation at the `/swagger-ui.html` endpoint.

## Contributions

Contributions are very welcome! If you want to contribute, fork the repository and create a pull request.

## Contact

If you have any suggestions or questions, please contact me on [LinkedIn](https://www.linkedin.com/in/gabrieudev).

---

**License:** This project is licensed under the terms of the [GNU General Public License (GPL)](LICENSE).