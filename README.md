# Library Management System

A full-stack library management application built with Java 21, Spring Boot, Spring Data JPA, PostgreSQL, Flyway and a responsive HTML/CSS/JavaScript frontend. It manages the book catalog, members, active loans, returns and overdue-loan indicators.

## Features

- Book catalog with search, create, update and delete operations.
- Member management with email uniqueness and member types.
- Loan creation with due dates (14 days for students/admins, 30 days for teachers), loan history and returns.
- Business rules prevent borrowing an unavailable book, returning a non-active loan, or deleting a book/member with active loans.
- PostgreSQL persistence, schema migrations and relational constraints.
- REST API with request validation and consistent JSON error responses.
- Dashboard metrics, responsive UI and automated tests.

## Stack

- Java 21, Spring Boot 3.5, Spring Web, Spring Data JPA and Jakarta Validation
- PostgreSQL 16 and Flyway migrations
- HTML, CSS and vanilla JavaScript
- JUnit 5, Mockito, H2 for tests, Docker Compose for local PostgreSQL

## Requirements

Install Java 21, Maven 3.9+ and Docker Desktop (or a local PostgreSQL 16 instance).

## Run locally

1. Copy `.env.example` to `.env` and change `DB_PASSWORD` to a local development password. The defaults in `docker-compose.yml` are only for local development.
2. Start PostgreSQL:
   ```bash
   docker compose up -d postgres
   ```
3. Start the application:
   ```bash
   mvn spring-boot:run
   ```
4. If you changed the database password in `.env`, export the same `DB_PASSWORD` in the terminal running Spring Boot (PowerShell: `$env:DB_PASSWORD="your_password"`; macOS/Linux: `export DB_PASSWORD="your_password"`). Docker Compose reads `.env` automatically, but Spring Boot does not load that file by itself.
5. Open `http://localhost:8080`. Flyway creates the database tables on startup.
6. Explore the interactive API docs at `http://localhost:8080/swagger-ui.html` or the OpenAPI JSON at `http://localhost:8080/v3/api-docs`.

The backend reads `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, `DB_PASSWORD`, `DB_URL` and `SERVER_PORT` from environment variables. Do not commit real credentials.

## Run tests

```bash
mvn test
```

Tests use an in-memory H2 database and do not require PostgreSQL to be running.

## REST API

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/dashboard` | Dashboard metrics |
| GET | `/api/books?q=java` | List/search books |
| POST | `/api/books` | Create a book |
| PUT | `/api/books/{id}` | Update a book |
| DELETE | `/api/books/{id}` | Delete a book when it has no active loan |
| GET | `/api/users` | List members |
| POST | `/api/users` | Register a member |
| PUT | `/api/users/{id}` | Update a member |
| DELETE | `/api/users/{id}` | Delete a member when there are no active loans |
| GET | `/api/loans?active=true` | List active loans (omit parameter for history) |
| POST | `/api/loans` | Create a loan: `{ "bookId": "UUID", "userId": "UUID" }` |
| POST | `/api/loans/{id}/return` | Return an active loan |

Example book request:

```json
{
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "publicationYear": 2008,
  "isbn": "9780132350884"
}
```

Errors return JSON with `timestamp`, `status`, `error` and `message`. Validation errors use HTTP 400, missing records use 404, and business-rule conflicts use 409.

## Architecture

The application follows a controller/service/repository structure. Controllers expose HTTP endpoints, services enforce business rules, repositories use Spring Data JPA, entities model relational data and DTOs define the API contract. Flyway owns the production schema; Hibernate validates it rather than modifying it automatically.

```text
Browser (static frontend) -> REST controllers -> services -> JPA repositories -> PostgreSQL
```

## Important scope notes

This is a portfolio/learning project, not a production-ready library deployment. Authentication, authorization, rate limiting, audit logs and production secrets management are not included yet. Add these before exposing the application to the public internet. ISBN is optional; when supplied it must be unique.
