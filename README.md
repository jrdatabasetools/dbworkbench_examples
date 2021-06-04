# PL/SQL Connector Builder Example

These examples demonstrates all data types and PL/SQL Connector Builder used by [PL/SQL Enterprise Workbench](http://jr-database-tools.com).

All examples contain a normal executable main Java program and a JUnit test case.

## 3 Ways to use these Examples
- Using the PL/SQL Enterprise Workbench
- Running all or single JUnit tests
- Running the Java main programs in each module

## Using the PL/SQL Enterprise Workbench
- Use the Eclipse IDE and install the [PL/SQL Enterprise Workbench](http://jr-database-tools.com).
- Import this  __dbworkbench_example__  project and switch to the PL/SQL Enterprise Workbench perspective.
- From this perspective you can configure the Oracle database connection. Compile all PL/SQL types and run the PL/SQL Connector Builder manually.

## Running all or single JUnit Tests
- Java 11 or higher required.
- These examples are tested with Maven 3.6.3 (at least Maven 3.6.x should work).
- To run Client/Server tests like RMI, JEE, Spring-Remoting a running Docker installation is required. 
- Create a user and install all PL/SQL types under directory **SQL**.
- Configure the Oracle database credentials and configuration parameter in the parent **pom.xml**.
- Run **mvn clean install** from the command line.
- Running the Maven command from parent directory will execute all tests, to run individual tests jump into a module directory.

## Running the Java Main Programs in each Module
- Java 11 or higher required.
- These examples are tested with Maven 3.6.3 (at least Maven 3.6.x should work).
- Create a user and install all PL/SQL types under directory **SQL**.
- Configure the Oracle database credentials and configuration parameter in the parent **pom.xml**.
- A Maven run to generate the Java access is required, so run **mvn clean package -DskipTests=true**. The generated Java access code will be generated in directory **./target/generated-sources/connector**.
- Now you can use your favorite Java IDE to run each example.

## Detailed Module Information
Detailed information for every test example is documented in each module directory.
