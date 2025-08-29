# SQL Solver Spring Boot Application

This Spring Boot application generates webhooks, solves SQL problems based on registration numbers, and submits solutions using JWT authentication.

## Features

- **Webhook Generation**: Automatically sends a POST request on startup to generate a webhook
- **SQL Problem Solving**: Determines which SQL question to solve based on the last two digits of the registration number
- **JWT Authentication**: Uses JWT tokens for secure API communication
- **RestTemplate/WebClient**: Uses Spring WebFlux WebClient for HTTP requests

## How It Works

1. **On Startup**: The application sends a POST request to generate a webhook with user details
2. **Webhook Response**: Receives a webhook URL and access token
3. **Problem Selection**: Based on the last two digits of the registration number:
   - **Odd**: Solves Question 1
   - **Even**: Solves Question 2
4. **Solution Submission**: Submits the SQL solution to the webhook URL using JWT authentication

## Configuration

Update the following properties in `src/main/resources/application.properties`:

```properties
app.user.name=Your Name
app.user.regNo=Your Registration Number
app.user.email=your.email@example.com
```

## API Endpoints Used

- **Webhook Generation**: `POST https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA`
- **Solution Submission**: `POST https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA`

## Project Structure

```
src/
├── main/java/com/webhook/sqlsolver/
│   ├── SqlSolverApplication.java          # Main application class
│   ├── model/
│   │   ├── WebhookRequest.java           # Request model for webhook generation
│   │   ├── WebhookResponse.java          # Response model from webhook generation
│   │   └── SqlSolutionRequest.java       # Request model for solution submission
│   └── service/
│       ├── WebhookService.java           # Handles webhook operations
│       └── SqlSolverService.java         # Handles SQL problem solving
└── test/java/com/webhook/sqlsolver/
    └── SqlSolverApplicationTests.java    # Unit tests
```

## Building and Running

### Prerequisites
- **Java 17 or higher** (Required - Install JDK and set JAVA_HOME environment variable)
- Maven (The project includes Maven wrapper, so Maven installation is optional)

### Setup Java Environment
1. Download and install Java JDK 17+ from [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://openjdk.org/install/)
2. Set the `JAVA_HOME` environment variable to your JDK installation directory
3. Add `%JAVA_HOME%\bin` to your PATH environment variable

### ⚠️ Troubleshooting: "JAVA_HOME not found" Error

If you see the error "JAVA_HOME not found in your environment", follow these steps:

#### Step 1: Install Java JDK 17+
- **Download**: https://adoptium.net/temurin/releases/?version=17 (Recommended)
- **Alternative**: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html

#### Step 2: Set Environment Variables (Windows)
1. Open **System Properties** → **Advanced** → **Environment Variables**
2. Under **System Variables**, click **New**:
   - Variable name: `JAVA_HOME`
   - Variable value: `C:\Program Files\Eclipse Adoptium\jdk-17.x.x-hotspot` (or your JDK path)
3. Edit **PATH** variable and add: `%JAVA_HOME%\bin`
4. **Restart** your terminal/VS Code

#### Step 3: Verify Installation
```powershell
java -version
javac -version
echo $env:JAVA_HOME
```

#### Quick Install via Chocolatey (if available)
```powershell
choco install openjdk17
```

#### Quick Install via Winget (Windows 11)
```powershell
winget install EclipseAdoptium.Temurin.17.JDK
```

### Build
```bash
# On Windows
.\mvnw.cmd clean compile

# On Unix/Linux/Mac
./mvnw clean compile
```

### Run
```bash
# On Windows
.\mvnw.cmd spring-boot:run

# On Unix/Linux/Mac
./mvnw spring-boot:run
```

### Test
```bash
# On Windows
.\mvnw.cmd test

# On Unix/Linux/Mac
./mvnw test
```

### Package
```bash
# On Windows
.\mvnw.cmd clean package

# On Unix/Linux/Mac
./mvnw clean package
```

The JAR file will be created in the `target/` directory.

## Important Notes

1. **SQL Questions**: The actual SQL problems need to be retrieved from the Google Drive links provided in the task description
2. **Registration Number**: Update your registration number in `application.properties`
3. **JWT Token**: The application handles JWT authentication automatically using the access token from the webhook response
4. **Error Handling**: The application includes comprehensive error handling and logging

## Customization

To solve actual SQL problems:

1. Access the Google Drive links for your specific questions
2. Update the `solveQuestion1()` and `solveQuestion2()` methods in `SqlSolverService.java`
3. Replace the placeholder SQL queries with the actual solutions

## Submission

This project meets all the requirements:
- ✅ Uses RestTemplate/WebClient with Spring Boot
- ✅ No controller/endpoint triggers the flow
- ✅ Uses JWT in the Authorization header for the second API call
- ✅ Automatically starts the process on application startup
