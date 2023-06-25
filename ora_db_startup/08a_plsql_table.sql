alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

create or replace package plsql_table
/**
 * Demonstration of PL/SQL table types.
 */
as

-- only Pl/Sql tables of number and varchar2 are supported by the the jdbc driver
type t_number_plsql_table is table of number(9) index by binary_integer;
type t_varchar2_plsql_table is table of varchar2(100) index by binary_integer;


/**
 * Demo summarizes all elements of i_number_table and count all characters in varchar2 PL/SQL table.
 *
 * @param i_number_table PL/SQL table of integers.
 * @param i_varchar2_table PL/SQL table of varchar2.
 * @param o_number_table_sum Sum of all integers.
 * @param o_varchar2_table_length Sum of length of all varchar2 elements.
 */
procedure doit
(
  i_number_table          in  t_number_plsql_table,
  i_varchar2_table        in  t_varchar2_plsql_table,
  o_number_table_sum      out number,
  o_varchar2_table_length out number
);

end plsql_table;
/