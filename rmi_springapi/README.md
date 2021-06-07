# RMI PL/SQL Connector Builder (SpringApi)

This example demonstrates the use of the RMI PL/SQL Connector Builder generating the client Java classes and the remote server Java classes registered to the RMI Registry.

The server is prepared to run in a Docker container for the JUnit tests.

A simple PL/SQL User Defined Object is transfered from a stored procedure to the Java client.

## Configuration

- Configure the Oracle database URL, credentials and configuration parameters in **parent/pom.xml** in the **properties** section.

## PL/SQL Operations

- Compile the PL/SQL stored procedure **SERVER_INFO_SIMPLE_OBJECT** and the User Defined Type **SIMPLE_OBJECT** to the Oracle database.


## Maven Docker Build

The Maven build process does the following configurations:

- Server Build:
      - Creating a Docker image based on Java 11.
      - Copy the target-jar file into the image.
- Client Build:
      - Start the Docker container.

## Run the JUnit Test

- Oracle database configuration is set in **parent/pom.xml** in the **properties** section.
- PL/SQL object is compiled to database.
- PL/SQL function is compiled to database.
- Run **mvn clean install** from directory containing the client and the server module.
