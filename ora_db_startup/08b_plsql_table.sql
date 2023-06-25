alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

create or replace package body plsql_table
as

procedure doit
(
  i_number_table          in  t_number_plsql_table,
  i_varchar2_table        in  t_varchar2_plsql_table,
  o_number_table_sum      out number,
  o_varchar2_table_length out number
)
is
  
begin
  o_number_table_sum := 0;
  for i in 1..i_number_table.count loop
    o_number_table_sum := o_number_table_sum + i_number_table(i);
  end loop;
  
  o_varchar2_table_length := i_varchar2_table.count;
end doit;

end plsql_table;
/