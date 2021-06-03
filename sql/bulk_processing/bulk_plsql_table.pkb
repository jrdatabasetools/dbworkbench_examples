create or replace package body bulk_plsql_table
as

procedure bulk_plsql_table
(
  i_plsqltab_number   in plsql_table_number, 
  i_plsqltab_date     in plsql_table_date, 
  i_plsqltab_varchar  in plsql_table_varchar
)
is
begin
  forall i in 1..i_plsqltab_number.count
    insert into bulk_processing_table
      (n,d,s)
      values
      (i_plsqltab_number(i), to_date(i_plsqltab_date(i), 'yyyy/mm/dd'), i_plsqltab_varchar(i));
end bulk_plsql_table;

end bulk_plsql_table;