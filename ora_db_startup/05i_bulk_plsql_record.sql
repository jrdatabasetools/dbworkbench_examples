alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

create or replace package body bulk_plsql_record
as

/**
 * Bulk collection demo with PL/SQL Record element.
 * 
 * @param i_table_plsql_record List of record elements of type 'plsql_record' to insert into table 'bulk_processing_table'.  
 */
procedure doit
(
  i_table_plsql_record   in table_plsql_record 
)
is
begin
  forall i in 1..i_table_plsql_record.count
    insert into bulk_processing_table
      (n,d,s)
      values
      (i_table_plsql_record(i).n, i_table_plsql_record(i).d, i_table_plsql_record(i).s);
end doit;

end bulk_plsql_record;
/