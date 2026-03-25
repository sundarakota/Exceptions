# Exceptions Demo API

Simple Spring Boot project to practice global exception handling.

## What this project shows

- `@RestControllerAdvice` in `src/main/java/org/example/exceptions/GlobalExceptionHandler.java`
- RFC 7807 Problem Details (`application/problem+json`) for API errors
- Demo endpoints in `src/main/java/org/example/exceptions/DemoController.java`

## Prerequisites

- Java 17+ (`pom.xml` sets `java.version` to `17`)
- macOS/Linux terminal (commands below use `zsh`/bash style)

## Run the application

From project root (`/Users/sundarakota/IdeaProjects/Exceptions`):

```zsh
cd /Users/sundarakota/IdeaProjects/Exceptions
chmod +x mvnw
./mvnw spring-boot:run
```

If port `8080` is already in use, run on another port (example `8081`):

```zsh
cd /Users/sundarakota/IdeaProjects/Exceptions
./mvnw spring-boot:run -Dspring-boot.run.arguments=--server.port=8081
```

## Test with Postman

Create a new request in Postman and use method `GET`.

Or import the ready-to-use collection file:

- `postman/Exceptions.postman_collection.json`
- In Postman: `Import` -> `Upload Files` -> select the file

### 1) Arithmetic exception demo

- URL: `http://localhost:8080/demo/divide-by-zero`
- If running on custom port: `http://localhost:8081/demo/divide-by-zero`

Expected response:

- Status: `400 Bad Request`
- Body:

```json
{
  "type": "https://api.example.com/problems/invalid-arithmetic",
  "title": "Invalid Arithmetic Operation",
  "status": 400,
  "detail": "Mathematical error: cannot divide by zero.",
  "instance": "/demo/divide-by-zero",
  "timestamp": "2026-03-24T21:09:10.079795Z"
}
```

### 2) Generic exception demo

- URL: `http://localhost:8080/demo/unexpected-error`
- If running on custom port: `http://localhost:8081/demo/unexpected-error`

Expected response:

- Status: `500 Internal Server Error`
- Body:

```json
{
  "type": "https://api.example.com/problems/internal-server-error",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "Unexpected server error.",
  "instance": "/demo/unexpected-error",
  "timestamp": "2026-03-24T21:20:00.000000Z"
}
```

## Run tests

```zsh
cd /Users/sundarakota/IdeaProjects/Exceptions
./mvnw test
```

This includes:

- `src/test/java/org/example/exceptions/ExceptionsApplicationTests.java`
- `src/test/java/org/example/exceptions/GlobalExceptionHandlerUnitTest.java`

## Troubleshooting

- `Permission denied: ./mvnw`
  - Run: `chmod +x mvnw`
- `Port 8080 was already in use`
  - Run with another port, e.g. `--server.port=8081`
- `Unsupported class file version` or Java mismatch
  - Check Java: `java -version`
  - Use Java 17 or newer

## Useful files

- `src/main/java/org/example/exceptions/ExceptionsApplication.java`
- `src/main/java/org/example/exceptions/DemoController.java`
- `src/main/java/org/example/exceptions/GlobalExceptionHandler.java`
- `pom.xml`

