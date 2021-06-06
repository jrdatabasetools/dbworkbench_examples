# JavaEE PL/SQL Connector Builder (SpringApi)

This example demonstrates the use of the JavaEE PL/SQL Connector Builder generating the client Java classes and the remote server EJBs.

The server is prepared to run in a Docker container. This is the easiest way to run the example as JUnit test. All configurations are done by the Maven build process.

A simple User Defined Object is transfered from a stored procedure to the Java client.

## Configuration

### Manual Configuration

- Configure the Oracle database URL, credentials and configuration parameters in **parent/pom.xml** in the **properties** section.

### Maven Automatic Configuration

The Maven build process does the following configurations:

- Creating a Docker image based on Wildfly.
    - Configures and installs the Oracle JDBC driver as module (correspondents to **server/jboss-deployment-structure.xml**).
    - Creating a default user for remote access (**Dockerfile** entry correspondent to **client/wildfly-config.xml**).
    - Installs a prepared **standalone.xml** file with a Oracle preconfigured DataSource using environment parameter as Oracle database parameter.
    - Installs the Application-EJBs in the Wildfly deployment directory.
- Running the Docker image.
    - Start the Docker container and pass the Oracle database parameter as environment variables.  

## PL/SQL Operations

- Compile the PL/SQL stored procedure **SERVER_INFO_SIMPLE_OBJECT** and the User Defined Type **SIMPLE_OBJECT** to the Oracle database.

## Server Module 'java_ee_springapi_server'

- The server part of the JavaEE PL/SQL Connector Builder generates all EJBs calling the PL/SQL stored function.
- No any further Java programming is necessary.
- **jboss-deployment-structure.xml** configures the access to the Oracle JDBC driver - important to access the Oracle JDBC extensions.
- **Dockerfile** defines the Docker image configuration.
- Maven **pom.xml** creates the Docker image.

## Client Module 'java_ee_springapi_client'

- The context to call the application server is configured in the JUnit test class **ServerInfoTest** as Spring Bean.
- **jndiModulePrefix** is configured in the JavaEE PL/SQL Connector Builder ( current setting : #{systemProperties.modulePrefix} ).
- **wildfly-config.xml** defines the user credentials to call the application server.
- Maven **pom.xml** starts the Docker container and pass the Oracle database parameter.
- Java test class **ServerInfoTest** is executed.

## Run the JUnit Test

- Oracle database configuration is set in **parent/pom.xml** in the **properties** section.
- PL/SQL object is compiled to database.
- PL/SQL function is compiled to database.
- Run **mvn clean install** from directory containing the client and the server module.
