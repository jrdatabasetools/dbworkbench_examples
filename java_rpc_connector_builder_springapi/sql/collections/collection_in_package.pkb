create or replace package body collection_in_package
as

procedure do_it
(
  i_col_number      in     number_table_in_package,
  io_col_timestamp  in out timestamp_table_in_package,
  o_col_varchar     out    varchar_varray_in_package
)
is
begin
  for i in 1..greatest(io_col_timestamp.count, i_col_number.count) loop
    if (i <= io_col_timestamp.count) then
      if (i <= i_col_number.count) then
        -- add <n> days to timestamp of number list 
        io_col_timestamp(i) := io_col_timestamp(i) + i_col_number(i);
      else 
        -- add <n> days to timestamp from random value
        io_col_timestamp(i) := io_col_timestamp(i) + dbms_random.value(-3000,3000);
      end if;
    else
      -- add <n> days of current database systimestamp
      io_col_timestamp.extend();
      io_col_timestamp(i) := systimestamp + i_col_number(i);
    end if;
  end loop;
  
  -- fill string collection with formatted timestamp values
  o_col_varchar := varchar_varray_in_package();
  for i in 1..least(io_col_timestamp.count, o_col_varchar.limit) loop
    o_col_varchar.extend();
    o_col_varchar(i) := to_char(io_col_timestamp(i));
  end loop;
end do_it;

end collection_in_package;