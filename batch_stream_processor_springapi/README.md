# Batch/Stream Processor Example (SpringApi)

This example demonstrates the use of the Batch/Stream-Processor PL/SQL Connector Builder. This is used when you process data row-by-row without loading all data into memory.

The PL/SQL procedure/functions returns one **Strong/Typed Cursor**. This is required for Batch/Stream Processing. Java Streams are used to process every single row. It is highly recommend not to use stream operations fetching all data at once.

## Configuration
The SpringApi requires the configuration of a DataSource as Spring Bean. The class **BatchStreamProcessorSpringApi** produces this DataSource Spring been.

- For the JUnit test these system properties are set in the **parent/pom.xml** in the **properties** section.
- To run the Java main class, modify the settings in the beginning of the file **BatchStreamProcessorSpringApi**.

## PL/SQL Package 'BATCH_STREAM_PROCESSOR'
Compile this package from the PL/SQL Enterprise Workbench or any other tool like SQL-Developer oder SQL-Plus to the Oracle Database.

## Run Java Test Class

- Modify the Oracle datebase settings **parent/pom.xml**.
- Compile the PL/SQL Package **BATCH_STREAM_PROCESSOR** to the Oracle database.
- Run **mvn clean package** from the command line to execute the test of Java test class **BatchStreamProcessorSpringApiTest**.

## Run Java Main Class

- Modify the system parameter settings in the beginning in the Java class **BatchStreamProcessorSpringApi**.  
- Compile the PL/SQL Package **BATCH_STREAM_PROCESSOR** to the Oracle database.
- To Generate the Java access classes, do one of the following steps:
      - Run the PL/SQL Connector Builder **batch_stream_processor_springapi** from the PL/SQL Enterprise Workbench.
      - Run **mvn clean package -DskipTests=true** from the command line.
- Start the Java main program. 

