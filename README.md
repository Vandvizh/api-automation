# API Automation Project

API test automation project built with Java and REST Assured.

The project demonstrates API testing practices, test organization, request/response handling, and basic CI automation.

## Tech Stack

* Java 25
* Maven
* JUnit 6
* REST Assured
* Jackson
* GitHub Actions

## API

The project uses the [JSONPlaceholder](https://jsonplaceholder.typicode.com/) REST API and focuses on the `/users` endpoint.

## Test Coverage

The project currently covers:

* GET users
* GET user by ID
* GET non-existing user
* POST user
* PUT user
* DELETE user

Tests validate HTTP status codes and, where applicable, response data.

Parameterized tests are used for scenarios with multiple test data sets.

## Project Structure

```text
src/test/java
├── clients
│   └── UserClient.java
├── config
│   └── Config.java
├── models
│   └── User.java
├── specifications
│   └── ResponseSpecs.java
└── tests
    └── ApiTest.java
```

### Architecture

* `clients` — API request methods
* `models` — data models used for request and response serialization
* `config` — common configuration
* `specifications` — reusable response specifications
* `tests` — API test scenarios

## CI

Tests are automatically executed using GitHub Actions on pushes and pull requests to the `master` branch.

## How to Run

Clone the repository and run:

```bash
mvn test
```
