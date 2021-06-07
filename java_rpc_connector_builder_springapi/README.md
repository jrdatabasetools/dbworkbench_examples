# JavaRPC PL/SQL Connector Builder All Data Types Examples (SpringApi)

This examples demonstrates the use of the JavaRPC PL/SQL Connector Builder.

The examples demonstrates all supported data types and can be run as JUnit tests all at once or run as Java programs individually.

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

## Java Examples Programs

Only the Java program examples can be run individually - the JUnit tests can only be run from the command line due to initialization.

- **BinaryTypesSpringApi**: Using PL/SQL binary types as parameter.
- **BulkCollectionObjectsSpringApi**: Bulk processing inserting PL/SQL Collections into table.
- **BulkCollectionPlSqlRecordSpringApi**: Bulk processing inserting PL/SQL Records into table.
- **BulkCollectionPlSqlTableSpringApi**: Bulk processing inserting PL/SQL Tables into table.
- **BulkCollectionScalarSpringApi**: Bulk processing inserting PL/SQL collection of scalar data types into table.
- **BulkSaveExceptionsSpringApi**: Bulk processing fetch and return PL/SQL exceptions.
- **BulkSelectCollectionObjectsSpringApi**: Bulk processing fetching PL/SQL Collection of User Defined Type Objects.
- **CollectionsOfObjectsSpringApi**: Using PL/SQL Collections of Objects.
- **CollectionsOfPlSqlRecordsSpringApi**: Using PL/SQL Collections of PL/SQL Records.
- **CollectionsOfScalarInPackageTypesSpringApi**: Using in package defined PL/SQL Collections of scalar data types.
- **CollectionsOfScalarTypesSpringApi**: Using PL/SQL Collections of sclar data types.
- **PlSqlRecordSpringApi**: Using PL/SQL Records as parameter.
- **PlSqlTableSpringApi**: Using PL/SQL Table as parameter.
- **RaiseApplicationErrorSpringApi**: Raise a PL/SQL application error.
- **ScalarTypesSpringApi**: Simple scalar types example.
- **ServerInfoSpringApi**: Fetching server Oracle database information returning a transfer object.
- **ServerInfoSimpleObjectSpringApi**: Fetching server Oracle database information returning a User Defined Type Object.
- **SubclassedObjectsSpringApi**: Example show working with sub classes User Defined Type Objects.
- **TypedRefCursorCollectionSpringApi**: Handling of Strong/Typed reference cursors containing a PL/SQL Collection.
- **TypedRefCursorObjectSpringApi**: Handling of Strong/Typed reference cursors containing a User Defined Type Object.
- **TypedRefCursorPlSqlRecordSpringApi**: Handling of Strong/Typed reference cursors returning a PL/SQL Record. 


## Run All JUnit Tests

- Oracle database configuration is set in **parent/pom.xml** in the **properties** section.
- Compile all PL/SQL types and stored procedures to the Oracle database.
- Run **mvn clean install** from command line.
