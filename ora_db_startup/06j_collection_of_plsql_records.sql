alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

create or replace package collection_of_plsql_records
as
/**
 * Demonstration of calling in-package defined collections of element type PL/SQL Record.
 */

type plsql_record is record (
  d           date,
  ts          timestamp,
  s           varchar2(100)
);
type table_plsql_record is table of plsql_record;

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
);
  
end collection_of_plsql_records;
/