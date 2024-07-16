# URL Shortener

![Java](https://img.shields.io/badge/Java-17-orange) ![JavaScript](https://img.shields.io/badge/JavaScript--yellow) ![HTML](https://img.shields.io/badge/HTML-5-blue) ![CSS](https://img.shields.io/badge/CSS-3-blue) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green) [![LinkedIn](https://img.shields.io/badge/Connect%20on-LinkedIn-blue)](https://www.linkedin.com/in/gabrieudev) ![GPL License](https://img.shields.io/badge/License-GPL-blue)

Welcome to my **URL Shortener** project.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
- [Endpoints](#endpoints)
- [Contributions](#contributions)
- [Contact](#contact)

## Introduction

The main goal of this project is to provide a RESTful API capable of receiving a URL, storing it in a database, and redirecting to the original URL upon request. Additionally, the project implements authentication with JWTs and role-based authorization for users, utilizing the latest and best practices to ensure the integrity of sensitive data.

## Features

- User login and registration.
- Email confirmation for registration.
- URL shortening.
- Subscription plans for URLs without expiration.
- URL access count visualization.
- Web page with HTML, CSS, and JavaScript.
- Integration with MySQL database.
- Swagger documentation for each endpoint.

## Technologies

- ![Java](https://img.shields.io/badge/Java-17-orange): Programming language used in the backend.
- ![JavaScript](https://img.shields.io/badge/JavaScript--yellow): Programming language used in the frontend.
- ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green): Framework used for building applications.
- ![MySQL](https://img.shields.io/badge/MySQL-Database-blue): Relational database.
- ![HTML](https://img.shields.io/badge/HTML-5-blue): Markup language.
- ![CSS](https://img.shields.io/badge/CSS-3-blue): Styling tool.

## Getting Started

Follow these steps to run the project on your machine (Docker is required):

1. Clone the repository: `git clone https://github.com/gabrieudev/url-shortener.git`
2. Navigate to the project directory: `cd <path>`
3. Navigate to the docker directory: `cd docker`
4. Run the docker compose file to create and start the MySQL container: `docker compose up`
5. Go back to the initial directory: `cd <path>`
6. Build the project: `./mvnw clean install` (for Windows: `mvnw.cmd clean install`)
7. Run the application: `./mvnw spring-boot:run` (for Windows: `mvnw.cmd spring-boot:run`)
8. Access the web page: `http://localhost:8080`

## Endpoints

User:

- `POST /auth/register`: Registers a user and sends a confirmation link to their email.
- `GET /users/confirm`: Verifies the email.
- `POST /auth/login`: Logs in and receives a JWT.
- `ADMIN Role` `GET /users`: Retrieves all users.
- `ADMIN Role` `DELETE /users/{userId}`: Deletes a user.
- `BASIC Role` `GET /users/{userId}`: Retrieves a user by ID.
- `BASIC Role` `POST /users/change-password`: Changes a user's password.

URL:

- `BASIC Role` `POST /shorten`: Shortens a URL with random characters.
- `BASIC Role` `POST /shorten/custom`: Shortens a URL with custom characters.
- `GET /r/{token}`: Redirects from a shortened URL to the full URL.
- `BASIC Role` `GET /r/{token}/count`: Retrieves the access count for the shortened URL.
- `ADMIN Role` `DELETE /r/{token}`: Deletes a shortened URL.

User subscription plan:

- `BASIC Role` `POST /user-subscription/change`: Chooses a subscription plan.

Access the complete documentation at the endpoint `/swagger-ui.html`

## Contributions

Contributions are very welcome! If you would like to contribute, fork the repository and create a pull request.

## Contact

If you have any suggestions or questions, contact me on [LinkedIn](https://www.linkedin.com/in/gabrieudev)

---

**License:** This project is licensed under the terms of the [GNU General Public License (GPL)](LICENSE).