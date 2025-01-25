# Desafío Técnico: Gestión de Tareas con Spring Boot y Java

API for task management at the company NUEVO SPA.
Only logged-in users can create and consume data.

The API keeps a historical record of users' tasks, displaying the current one.

## Requirements

- Java 17 >=
- Maven
- Git

## Installation and Execution

```bash
# install dependencies
mvn clean install
# run the application
mvn spring-boot:run # running at http://localhost:8080/
```

## Usage

The first step to use the API is to authenticate; otherwise, a 403 error will be returned.

To view the documentation, access Swagger at this [link](http://localhost:8080/swagger-ui/index.html)

### Authentication

URL: http://localhost:8080/api/auth
Accepts username and password

```
{
  "username": "string",
  "password": "string"
}
```

Available users: user1, user2, user3. All share the same password: "123" (without quotes).

If you're using Postman, you can import the file with endpoints located in `src/main/resources/uses_cases.postman_collection.json`.

> [!NOTE]
> The Postman project is configured so that once logged in, you don't need to manually add the token to each request header, as it will be handled **AUTOMATICALLY**.

### Consuming Other Services

As mentioned, the Postman file contains all the examples to test the endpoints.

## Development

The project follows a clean architecture approach to separate domain elements, business rules, and the implemented technology, aiming for scalability and ease of maintenance.

## Structure of project

The project is developed with Springboot 3.4 y Maven mainly. It follow a Clean Architecture like this:

## Structure of project

The project is developed with Spring Boot 3.4 and Maven mainly. It follows a Clean Architecture like this:

```
desafio-spring-boot/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── desafio_spring_boot/
│   │   │               ├── config/                # Spring and security configuration
│   │   │               ├── controller/            # REST controllers
│   │   │               ├── domain/                # Domain entities
│   │   │               │   ├── auth/              # Entities related to auth
│   │   │               │   ├── task/              # Entities related to tasks
│   │   │               │   ├── status_task/       # Entities related to task status
│   │   │               │   └── user/              # Entities related to users
│   │   │               ├── exception/             # Exception handling
│   │   │               ├── repository/            # Data access repositories
│   │   │               └── service/               # Business services
│   │   ├── resources/                             # Static resources and configuration
│   │   │   ├── application.properties             # Application configuration
│   │   │   ├── data.sql                           # Initial load of data
│   │   │   ├── openapi.yaml                       # Schema First API
│   │   │   └── uses_cases.postman_collection.json # Postman collection for testing
├── .gitignore                                     # Files and directories ignored by Git
└── pom.xml                                        # Maven configuration file
```

## Commit convention

This project follow a standard for commit messages based on [Angular Conventions](https://github.com/angular/angular/blob/22b96b9/CONTRIBUTING.md#-commit-message-guidelines).

Each commit message consists of a header, a body and a footer. The header has a special format that includes a type, a scope and a subject:

```
<type>(<scope>): <subject>
<BLANK LINE>
<body>
<BLANK LINE>
<footer>
```

The header is mandatory and the scope of the header is optional.

Any line of the commit message cannot be longer 100 characters! This allows the message to be easier to read on GitHub as well as in various git tools.

### Samples:

```
docs(changelog): update changelog to beta.5
```

```plaintext
fix(release): need to depend on latest rxjs and zone.js
The version in our package.json gets copied to the one we publish, and users need the latest of these.
```

### Types

Must be one of the following:

- **build:** Changes that affect the build system or external dependencies (example scopes: gulp, broccoli, npm)
- **ci:** Changes to our CI configuration files and scripts (example scopes: Travis, Circle, BrowserStack, SauceLabs)
- **docs:** Documentation only changes
- **feat:** A new feature
- **fix:** A bug fix
- **perf:** A code change that improves performance
- **refactor:** A code change that neither fixes a bug nor adds a feature
- **style:** Changes that do not affect the meaning of the code (white-space, formatting, missing semi-colons, etc)
- **test:** Adding missing tests or correcting existing tests
