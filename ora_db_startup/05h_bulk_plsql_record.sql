alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

create or replace package bulk_plsql_record
/**
 * Bulk collection demo package demonstration of collection of PL/SQL Records elements.
 */
as

type plsql_record is record (
  n    number,
  d    date,
  s    varchar2(100)
);
type table_plsql_record is table of plsql_record;

/**
 * Bulk collection demo with PL/SQL Record element.
 * 
 * @param i_table_plsql_record List of record elements of type 'plsql_record' to insert into table 'bulk_processing_table'.  
 */
procedure doit
(
  i_table_plsql_record   in table_plsql_record 
);

end bulk_plsql_record;
/