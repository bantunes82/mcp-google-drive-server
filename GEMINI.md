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

## Steps to follow before applying changes
1. **Pull Latest Changes**: Always start by pulling the latest changes from the main branch.
2. **Create a Feature Branch**: Use a descriptive name for your branch (e.g., `feature/add-new-endpoint`).
3. **Prepare your plan**: Outline the changes you plan to make, including any new features or bug fixes.
4. **Ask me to review your plan**: Before you start coding, share your plan with me for feedback.
5. **Implement Changes**: Make your changes in the feature branch.
6. **Test Your Changes**: Run unit tests and integration tests to ensure everything works as expected.
7. **Commit Your Changes**: Use clear and descriptive commit messages.
8. **Push Your Branch**: Push your changes to the remote repository.
9. **Create a Pull Request**: Open a pull request against the main branch, and request a review from me.



