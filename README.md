PMAY Integration - Example Spring Boot project
=============================================

This project is a starter template implementing the PMAY integration endpoints, SFTP polling via Spring Integration,
and JDBC-backed authentication (BCrypt). It is configured for Java 21 and Oracle DB.

Important:
 - Add an Oracle JDBC driver to your Maven repository or company Nexus/Artifactory.
 - Update application.yml with real Oracle and SFTP credentials.
 - The project creates a default user at startup if no users exist.

Build:
 mvn -U clean package

Run:
 mvn spring-boot:run

Download:
 The generated zip contains this project.
