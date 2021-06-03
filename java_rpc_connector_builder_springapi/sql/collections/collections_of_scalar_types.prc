create or replace procedure collections_of_scalar_types
(
  i_col_number      in     table_of_number,
  io_col_timestamp  in out table_of_timestamp,
  o_col_varchar     out    varray_of_varchar
)
/**
 * Demonstration of calling a stored procedure with in/put parameter of collections of scalar element types.
 *
 * @param i_col_number Table-Collection of integer input values.
 * @param io_col_timestamp Table-Collection of timestamp value.
 * @param o_col_varchar Varray(5)-Collection of varchar2 out put values.
 */
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
  o_col_varchar := varray_of_varchar();
  for i in 1..least(io_col_timestamp.count, o_col_varchar.limit) loop
    o_col_varchar.extend();
    o_col_varchar(i) := to_char(io_col_timestamp(i));
  end loop;
end collections_of_scalar_types;