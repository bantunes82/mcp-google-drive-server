# Gemini Code Assistant Project Overview

## Build & Run Commands
- Build: `./mvnw clean install`
- Run: `./mvnw spring-boot:run`
- Test all: `./mvnw test`
- Run single test: `./mvnw test -Dtest=ClassName#methodName`
- Example: `./mvnw test -Dtest=GoogleDriveApplicationTests#contextLoads`

## Configuration to run the application
    Configure your MCP client to connect to the server. See the `README.md` for detailed instructions on how to configure Claude Desktop.

## Code Style Guidelines
- **Package Structure**: Use `bantunes82.spring.ai.mcp.google.drive.server` namespace
- **Naming**:
    - Classes: PascalCase (e.g., `GoogleDriveService`)
    - Methods/Variables: camelCase (e.g., `uploadMicrosoftWordFile()`)
- **Imports**: Standard ordering (Java → Spring → other libraries)
- **Testing**: Use JUnit 5 and mockito for unit tests provided by Spring Boot Test framework,
and for integration tests use `@SpringBootTest` annotation with MockitoBean only if necessary.
- **Logging**: Use SLF4J for logging, avoid System.out.println
- **Error Handling**: Use Spring exception handlers for API errors
- **Documentation**: Add JavaDoc for public methods/classes

## Tech Stack
- Java 24
- Spring Boot 3.5.0
- Spring AI 1.0.0
- Google Drive API v3-rev20250427-2.0.0

### Prerequisites

-   Java 24+
-   Maven 3.8+
-   Google Cloud project with the Google Drive API enabled.
-   Application Default Credentials (ADC) configured for your local environment.

## Project Structure
Follow standard Spring Boot application architecture with services, and configuration as needed.



