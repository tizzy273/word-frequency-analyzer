# Word Frequency Analyzer

The Word Frequency Analyzer is a microservice for analyzing word frequencies in text documents. It provides endpoints for calculating the highest frequency, the frequency of a specific word, and the most frequent N words. Words are sequences of letters (a-z), case-insensitive, and non-letter characters act as separators. API documentation is available through [Swagger](http://localhost:8080/swagger-ui.html).

To run locally, clone the repository to your machine and run the project:

- **If you have Maven installed:** `mvn spring-boot:run`
- **macOS/Linux (no Maven needed):** `./mvnw spring-boot:run`
- **Windows (no Maven needed):** `mvnw.cmd spring-boot:run`

To run tests, replace `spring-boot:run` with `clean test` in any of the commands above.

## Features

- Calculate the highest word frequency in a text
- Calculate the frequency of a specific word in a text
- Retrieve the N most frequent words (sorted by frequency, alphabetically for ties)

A [Postman Collection](https://drive.google.com/uc?export=download&id=18XKye06vxAcyKB48lUGaBSwDZjy2M0IP) is available for testing the APIs.

## Assumptions

- Input text fits entirely in memory
- Digits and special characters are treated as word delimiters (e.g., `"hello123world"` is split into `hello` and `world`)
- Empty/blank input returns 0 or an empty list

## Technologies

- Java 25 and Spring Boot
- JUnit 5 and RestAssured (Testing)
- Swagger (API Documentation)
