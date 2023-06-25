alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

create or replace package body collection_of_plsql_records
as

/**
 * Demonstration of calling a stored procedure with input of table of PL/SQL record elements. The elements are modified and returned as output parameter.
 *
 * @param i_delta_d Offset is added to date value.
 * @param i_delta_ts Offset is added to timestamp value.
 * @param i_table_plsql_record Input list of collection PL/SQL Records.
 * @param o_table_plsql_record Output list of collection PL/SQL Records.
 */
procedure doit
(
  i_delta_d             in    number,
  i_delta_ts            in    number,
  i_table_plsql_record  in    table_plsql_record,
  o_table_plsql_record  out   table_plsql_record
)
is
begin
  o_table_plsql_record := table_plsql_record();
  
  -- append elements to collection
  for i in 1..i_table_plsql_record.count loop
    o_table_plsql_record.extend();
    o_table_plsql_record(i).d := i_table_plsql_record(i).d + i_delta_d + i;
    o_table_plsql_record(i).ts := i_table_plsql_record(i).ts + i_delta_ts + i;
    o_table_plsql_record(i).s := i_table_plsql_record(i).s || ' - ' || DBMS_RANDOM.string('A',10);
  end loop;
end doit;  


end collection_of_plsql_records;
/