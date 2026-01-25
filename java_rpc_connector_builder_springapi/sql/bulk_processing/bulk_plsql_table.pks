create or replace package bulk_plsql_table
/**
 * Bulk collection demo package demonstration in package defined PL/SQL tables.
 */
as

/** PL/SQL table type definitions */
type plsql_table_number is table of number index by pls_integer;
type plsql_table_date is table of varchar2(8) index by pls_integer;
type plsql_table_varchar is table of varchar2(100) index by pls_integer;

/**
 * Bulk collection demo with three input parameter of PL/SQL table.
 * Because only number and varchar2 elements are supported, the date value is converted to varchar2.
 * 
 * @param i_plsqltab_number List of number values to insert into table 'bulk_processing_table'.  
 * @param i_plsqltab_date List of date values to insert into table 'bulk_processing_table'.  
 * @param i_plsqltab_varchar List of varchar2 values to insert into table 'bulk_processing_table'.  
 */
procedure bulk_plsql_table
(
  i_plsqltab_number   in plsql_table_number, 
  i_plsqltab_date     in plsql_table_date, 
  i_plsqltab_varchar  in plsql_table_varchar
);

end bulk_plsql_table;