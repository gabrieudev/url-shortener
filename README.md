# RESTful API for URL Shortener

![Java](https://img.shields.io/badge/Java-17-orange) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green) [![LinkedIn](https://img.shields.io/badge/Connect%20on-LinkedIn-blue)](https://www.linkedin.com/in/gabrieudev) ![GPL License](https://img.shields.io/badge/License-GPL-blue)

Welcome to my **RESTful API for URL Shortener** project.

Please select your preferred language:

- [English](README.md)
- [Português (Brasil)](README.pt-br.md)

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
- [Endpoints](#endpoints)
- [Contributions](#contributions)
- [Contact](#contact)

## Introduction

The main goal of this project is to provide a RESTful API capable of shortening URLs. Additionally, it is also possible to customize characters and view the number of accesses to a shortened URL.

## Features

- URL shortening.
- Viewing the number of accesses to the URL.
- Integration with MySQL database.
- Documentation of each endpoint using Swagger.

## Technologies

- ![Java](https://img.shields.io/badge/Java-17-orange): Programming language.
- ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green): Framework used for building applications.
- ![MySQL](https://img.shields.io/badge/MySQL-Database-blue): Relational database.

## Getting Started

Follow these steps to run the project on your machine (Docker installation required):

1. Clone the repository: `git clone https://github.com/gabrieudev/url-shortener.git`
2. Navigate to the project directory: `cd <path>`
3. Navigate to the docker directory: `cd docker`
4. Run the docker compose file to create and initialize the MySQL container: `docker compose up`
5. Go back to the initial directory: `cd <path>`
6. Build the project: `./mvnw clean install` (for Windows: `mvnw.cmd clean install`)
7. Run the application: `./mvnw spring-boot:run` (for Windows: `mvnw.cmd spring-boot:run`)

## Endpoints

- `POST /shorten`: Shortens a URL with random characters.
- `POST /shorten/custom`: Shortens a URL with custom characters.
- `GET /r/{token}`: Redirects from a shortened URL to the full URL.
- `GET /r/{token}/count`: Gets the number of accesses to the shortened URL.
- `DELETE /r/{token}`: Deletes a shortened URL.

Access the full documentation at the `/swagger-ui.html` endpoint.

## Contributions

Contributions are very welcome! If you want to contribute, fork the repository and create a pull request.

## Contact

If you have any suggestions or questions, contact me on [LinkedIn](https://www.linkedin.com/in/gabrieudev).

---

**License:** This project is licensed under the terms of the [GNU General Public License (GPL)](LICENSE).