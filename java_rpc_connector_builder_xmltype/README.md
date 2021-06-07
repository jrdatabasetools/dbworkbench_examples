# JavaRPC PL/SQL Connector Builder XML_TYPE Examples (FactoryApi)

This examples demonstrates the use of the data type XML_TYPE.

## Configuration

- Configure the Oracle database URL, credentials and configuration parameters in **parent/pom.xml** in the **properties** section to use the JUnit tests.
- Set the Oracle database URL, credentials and configuration parameters in the beginning of the Java classes, when running the examples as Java main programs individually.

## PL/SQL Types and Stored Procedures

- **sql/xml_type_demo**: PL/SQL package using the data type XML_TYPE.

## Java Examples Programs

Only the Java program example and the JUnit test can be run individually.

- **XmlTypeDemo(Test)**: Using data type XML_TYPE as parameter.


## Run JUnit Test

- Oracle database configuration is set in **parent/pom.xml** in the **properties** section.
- Compile all PL/SQL types and stored procedures to the Oracle database.
- Run **mvn clean install** from command line.
