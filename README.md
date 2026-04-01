# PMAY Integration Service (Spring Boot 3.2.2)

This service provides XML (DSC Enrollment, FTO Request) and JSON integration for PMAY (Pradhan Mantri Awas Yojana) using Oracle Database and SFTP.

## Features
- **Oracle DB Integration**: Modern Hibernate 6 Dialect configuration.
- **Spring Security**: JDBC-backed authentication with BCrypt.
- **SFTP Integration**: Automatic polling of remote directories for XML file processing using Spring Integration 6.
- **OpenAPI/Swagger**: Built-in API documentation and testing interface.
- **Global Error Handling**: Professional JSON/XML error responses.

## Prerequisites
1. **Java 21**
2. **Docker Desktop** (Required for local Oracle and SFTP)
3. **Oracle SQL Developer**

## Quick Start (Docker Stack)
This project includes a [docker-compose.yml](file:///Users/pritamsharma/Documents/pmay/docker-compose.yml) to start both the Oracle DB and a test SFTP server:

1. **Start Docker Desktop** on your Mac.
2. **Launch the stack**:
   ```bash
   docker-compose up -d
   ```
   *Wait ~2 minutes for Oracle to initialize.*

3. **Database Schema**:
   - Open Oracle SQL Developer.
   - Connect to `localhost:1521` (Service Name: `FREE`, User: `system`, Password: `pmay_password`).
   - Run the script: [scripts/schema-oracle.sql](file:///Users/pritamsharma/Documents/pmay/scripts/schema-oracle.sql).
2. **Configuration**:
   - Update `src/main/resources/application.yml` with your Oracle host, port, service name, and SFTP credentials.
   - Example local URL: `jdbc:oracle:thin:@localhost:1521:xe`
3. **Build**:
   - `mvn clean install`
4. **Run**:
   - **For Oracle (Default)**: `mvn spring-boot:run` (Requires Oracle listener on `localhost:1521`)
   - **For Instant Testing (No Oracle needed)**: `mvn spring-boot:run -Dspring-boot.run.profiles=dev`
     - This uses an in-memory **H2 database**.
     - Access H2 Console at: [http://localhost:8080/h2-console](http://localhost:8080/h2-console) (JDBC URL: `jdbc:h2:mem:pmaydb`)

## API Documentation
Once the application is running, access the interactive Swagger UI and OpenAPI docs at:
- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **API Docs**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

## Default Security Credentials
- **Username**: `ifmisuser`
- **Password**: `ifmispassword`
- **Roles**: `IFMIS`

## Project Structure
- `com.aparsh.api.pmay.config`: Database, Security, and SFTP Flow configurations.
- `com.aparsh.api.pmay.controller`: REST API endpoints (JSON and XML).
- `com.aparsh.api.pmay.service`: Core business logic and XML unmarshalling.
- `com.aparsh.api.pmay.xml`: JAXB classes for PMAY XML formats.
- `scripts/`: SQL scripts for Oracle SQL Developer.

---
*Created and optimized for seamless integration with Oracle SQL Developer.*
