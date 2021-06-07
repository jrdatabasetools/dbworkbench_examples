# JavaRPC PL/SQL Connector Builder All Data Types Examples (FactoryApi)

This examples demonstrates the use of the JavaRPC PL/SQL Connector Builder.

The examples demonstrates all supported data types and can be run as JUnit tests individually or all at once or run as Java programs.

## Configuration

- Configure the Oracle database URL, credentials and configuration parameters in **parent/pom.xml** in the **properties** section to use the JUnit tests.
- Set the Oracle database URL, credentials and configuration parameters in the beginning of the Java classes, when running the examples as Java main programs individually.

## PL/SQL Types and Stored Procedures

Sub Paths:
- **binary_types**: PL/SQL function example with binary types parameter.
- **bulk_processing**: PL/SQL types and PL/SQL stored procedures for Bulk operations.
- **collections**: PL/SQL collection types and PL/SQL stored procedures for collection operations.
- **plsql_record**: PL/SQL package demonstrating PL/SQL records.
- **plsql_table**: PL/SQL package demonstrating PL/SQL tables.
- **raise_application_error**: PL/SQL procedure raising an exception.
- **scalar_types**: PL/SQL package and PL/SQL procedure working with scalar parameter types.
- **simple_object**: PL/SQL package and PL/SQL User Defined Object specification and body implementation showing object inspection in PL/SQL.
- **subclassed_objects**: PL/SQL package and User Defined Type derived Objects show data polymorphism.
- **typed_ref_cursor**: Strong/Typed reference cursors working with PL/SQL collections, PL/SQL records and User Defined Type Objects.

## Java Examples Programs and JUnit Tests

The Java examples and the JUnit tests can be run individually.

- **BinaryTypesFactoryApi(Test)**: Using PL/SQL binary types as parameter.
- **BulkCollectionObjectsFactoryApi(Test)**: Bulk processing inserting PL/SQL Collections into table.
- **BulkCollectionPlSqlRecordFactoryApi(Test)**: Bulk processing inserting PL/SQL Records into table.
- **BulkCollectionPlSqlTableFactoryApi(Test)**: Bulk processing inserting PL/SQL Tables into table.
- **BulkCollectionScalarFactoryApi(Test)**: Bulk processing inserting PL/SQL collection of scalar data types into table.
- **BulkSaveExceptionsFactoryApi(Test)**: Bulk processing fetch and return PL/SQL exceptions.
- **BulkSelectCollectionObjectsFactoryApi(Test)**: Bulk processing fetching PL/SQL Collection of User Defined Type Objects.
- **CollectionsOfObjectsFactoryApi(Test)**: Using PL/SQL Collections of Objects.
- **CollectionsOfPlSqlRecordsFactoryApi(Test)**: Using PL/SQL Collections of PL/SQL Records.
- **CollectionsOfScalarInPackageTypesFactoryApi(Test)**: Using in package defined PL/SQL Collections of scalar data types.
- **CollectionsOfScalarTypesFactoryApi(Test)**: Using PL/SQL Collections of sclar data types.
- **PlSqlRecordFactoryApi(Test)**: Using PL/SQL Records as parameter.
- **PlSqlTableFactoryApi(Test)**: Using PL/SQL Table as parameter.
- **RaiseApplicationErrorFactoryApi(Test)**: Raise a PL/SQL application error.
- **ScalarTypesFactoryApi(Test)**: Simple scalar types example.
- **ServerInfoFactoryApi(Test)**: Fetching server Oracle database information returning a transfer object.
- **ServerInfoSimpleObjectFactoryApi(Test)**: Fetching server Oracle database information returning a User Defined Type Object.
- **SubclassedObjectsFactoryApi(Test)**: Example show working with sub classes User Defined Type Objects.
- **TypedRefCursorCollectionFactoryApi(Test)**: Handling of Strong/Typed reference cursors containing a PL/SQL Collection.
- **TypedRefCursorObjectFactoryApi(Test)**: Handling of Strong/Typed reference cursors containing a User Defined Type Object.
- **TypedRefCursorPlSqlRecordFactoryApi(Test)**: Handling of Strong/Typed reference cursors returning a PL/SQL Record. 


## Run All JUnit Tests

- Oracle database configuration is set in **parent/pom.xml** in the **properties** section.
- Compile all PL/SQL types and stored procedures to the Oracle database.
- Run **mvn clean install** from command line.
