# Spring-HTTP-Remoting PL/SQL Connector Builder

This example demonstrates the use of the Spring-HTTP-Remoting PL/SQL Connector Builder generating the client Java classes and the remote server Spring Java classes. The Spring Server classes run on a Spring Boot Server.

The server is prepared to run in a Docker container for the JUnit tests.

PL/SQL User Defined Type object and subclassed object gets inspected by Java and the PL/SQL stored procedures.

## Configuration

- Configure the Oracle database URL, credentials and configuration parameters in **parent/pom.xml** in the **properties** section.

## PL/SQL Operations

- Compile the PL/SQL the types **BASE_OBJECT**, **DERIVED_OBJECT** and the PL/SQL package **SUBCLASSED_OBJECTS** to the Oracle database.

## Maven Docker Build

The Maven build process does the following configurations:

- Server Build:
      - Creating a Docker image based on Java 11.
      - Copy the copy the full assembled - including a complete Spring-Boot-server - target-jar file into the image.
- Client Build:
      - Start the Docker container.

## Run Server 'spring_http_remoting_server' Manually

- Run **mvn clean package -DskipTests=true** in the server directory to generate the server Java access classes.
- Configure the Oracle database parameter in the beginning of Java class **MainServerManualRun** and start it.

## Run Client Java Main Class Manually

- The server is running.
- Just run Java class **RmiServerInfoFactoryApi**.

## Run Client JUnit Test Manually

- The server is running.
- Just run Java main class **SubclassedObjectsFactoryApi** or **SubclassedObjectsSpringApi**.
- Uncomment the line setting the **baseUrl** and run JUnit test class **SubclassedObjectsFactoryApiTest**.


## Run the JUnit Test

- Oracle database configuration is set in **parent/pom.xml** in the **properties** section.
- PL/SQL objects are compiled to database.
- PL/SQL package is compiled to database.
- Run **mvn clean install** from directory containing the client and the server module.
