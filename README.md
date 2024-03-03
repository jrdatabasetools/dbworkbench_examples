# PL/SQL Connector Builder Examples

These examples demonstrates all data types and PL/SQL Connector Builders used by [PL/SQL Enterprise Workbench](https://www.jr-database-tools.com).

All examples contain a normal executable main Java program and a JUnit test case.

## Prerequisites

### Installed Software

- Java 11
- Docker 
- Maven 3.x

### Oracle Database XE 21c

- **Windows** : Run **run_oracle.cmd** from the command line of the project root to start a preconfigured database (git-bash will not work).
- **Linux/OSX** : Run **run_oracle.sh** from the command line of the project root to start a preconfigured database.
- An Oracle 21c XE (Express Edition) will be downloaded as Docker image.
- All Sql scripts in the folder **ora_db_startup** will be executed after startup.
- About 5 GB will be downloaded and require about 12 GB in the docker registry.

### Oracle Connection Info

- The default password for the administration users SYS, SYSTEM and PDBADMIN are set to 'oracle'.
- A preconfigured schema 'HR' identified by password 'hr' is created during setup.
- Url to the schema 'HR' is 'jdbc:oracle:thin:@localhost:1521/xepdb1'

## Data Types Examples
These examples demonstrates the use of all possible data types using the Java-RPC PL/SQL Connector Builder. The PL/SQL Connector Builder supports all typed data types of standard JDBC and Oracle JDBC Extensions.

- **java_rpc_connector_builder_factoryapi**: Examples of all supported data types using the Java-RPC PL/SQL Connector Builder with the FactoryApi.
- **java_rpc_connector_builder_springapi**: Examples of all supported data types using the Java-RPC PL/SQL Connector Builder with the SpringApi.
- **java_rpc_connector_builder_xmltype**: The **XML_TYPE** is extracted to an individual example project, because it depends on more Oracle libraries. 

## RAWJDBC Wrapper Examples
The PL/SQL RAWJDBC-Wrapper Connector Builder are used to have more control of transaction control. In every call the database connection is the first parameter.

- **rawjdbc_wrapper**: Simple example using the RAWJDBC PL/SQL Connector Builder calling the PL/SQL function **BINARY_TYPES**.

## Batch/Stream Examples
The Batch/Stream processing demonstrates the Batch/Stream Processing PL/SQL Connector Builder used for row-by-row processing without loading all data into memory.

- **batch_stream_processor_factoryapi**: Batch/Stream processing demo using a PL/SQL strong/typed cursor and Java Streams using the FactoryApi.
- **batch_stream_processor_springapi**: Same as **batch_stream_processor_factoryapi** using the SpringApi.

## Client/Server-Examples
All Client/Server-Examples demonstrate PL/SQL Connector Builder generating a client and server part. 

The JUnit tests are creating and running the server parts in a Docker container. The JUnit client parts are calling the server.

The server and the client can be run as Java main programs without using Docker (JavaEE client only).

- **java_ee_factoryapi**: Example of the JavaEE PL/SQL Connector Builder for remote calling EJB Beans containing the generated Java access code for PL/SQL procedures using the FactoryApi.
- **java_ee_springapi**: Same as **java_ee_factoryapi** using the SpringApi.
- **rmi_factoryapi**: Example of the RMI PL/SQL Connector Builder for remote calling RMI Beans containing the generated Java access code for PL/SQL procedures using the FactoryApi.
- **rmi_springapi**: Same as **rmi_factoryapi** using the SpringApi.
- **spring_http_remoting**: Example of the Spring-HTTP-Remoting PL/SQL Connector Builder for remote calling Java classes containing the generated Java access code for PL/SQL procedures using the SpringApi and FactoryApi.

## 3 Ways to run these Examples
1. Using the PL/SQL Enterprise Workbench
2. Running all or single JUnit tests
3. Running the Java main programs in each module

## Using the PL/SQL Enterprise Workbench
- Use the Eclipse IDE and install the [PL/SQL Enterprise Workbench](https://www.jr-database-tools.com).
- Import this **dbworkbench_example**  project and switch to the PL/SQL Enterprise Workbench perspective.
- From this perspective you can configure the Oracle database connection and compile all PL/SQL types and run the PL/SQL Connector Builder manually
- Running single scripts are not possible - to create a table and a user you have to use other tools like SQL-Developer.

## Running all or single JUnit Tests
- Run the shell in commands in following tested environments : shell (OSX/Mac/Linux) or git-bash (Windows).
- Java 11 or higher required.
- These examples are tested with Maven 3.6.3 (at least Maven 3.6.x should work).
- To run Client/Server tests like RMI, JEE, Spring-Remoting a running Docker installation is required. 
- Create a user and install all PL/SQL types under directory **SQL** (if you just want to run single tests/examples, install the PL/SQL types in the example **SQL** directory).
- Configure the Oracle database credentials and configuration parameter in the parent **pom.xml**.
- Run **mvn clean install** from the command line.
- Running the Maven command from parent directory will execute all tests, to run individual tests jump into a module directory.

## Running the Java Main Programs in each Module
- Java 11 or higher required.
- These examples are tested with Maven 3.6.3 (at least Maven 3.6.x should work).
- Create a user and install all PL/SQL types under directory **SQL**.
- Configure the Oracle database credentials and configuration parameter in the parent **pom.xml**.
- A Maven run to generate the Java access is required, so run **mvn clean package -DskipTests=true**. The Java access code will be generated into directory **./target/generated-sources/connector**.
- Now you can use your favorite Java IDE to run each example.
- You don't need Docker. Start the server program and after that the run client module.
