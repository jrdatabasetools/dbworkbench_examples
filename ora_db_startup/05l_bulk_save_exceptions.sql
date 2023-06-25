alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

create or replace function bulk_save_exceptions(i_col_object in bulk_table_object)
return bulk_table_varchar
/**
 * Demo how to process exceptions during bulk processing.
 * 
 * @param i_col_object List of object type values to insert into table 'bulk_processing_table'.
 * @return Collection of varchar2 containing exception information.  
 */
is
  result   bulk_table_varchar;
  ex_idx   number;
  ex_code  number;
begin
  result := bulk_table_varchar();
  
  begin
    forall i in 1..i_col_object.count save exceptions
      insert into bulk_processing_table
        (n,d,s)
        values
        (i_col_object(i).n, i_col_object(i).d, i_col_object(i).s);
  exception when others then
    for k in 1..sql%bulk_exceptions.count loop
      ex_idx := sql%bulk_exceptions(k).error_index;
      ex_code := sql%bulk_exceptions(k).error_code;
      result.extend();
      result(result.count) := 'idx:' || ex_idx || ' - msg:' || sqlerrm(-ex_code);
    end loop;
  end;
  
  return result;
end bulk_save_exceptions;
/