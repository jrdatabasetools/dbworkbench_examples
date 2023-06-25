alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

create or replace procedure collections_of_objects
(
  i_delta_d         in    number,
  i_delta_ts        in    number,
  i_col_objects     in    table_of_col_object,
  o_col_objects     out   table_of_col_object
)
/**
 * Demonstration of calling a stored procedure with input of table of object elements. The elements are modified and returned as out put parameter.
 *
 * @param i_delta_d Offset is added to date value.
 * @param i_delta_ts Offset is added to timestamp value.
 * @param i_col_objects Input list of collection objects.
 * @param o_col_objects Output list of collection objects.
 */
is
begin
  -- initialize output collection
  o_col_objects := table_of_col_object();

  -- append elements to collection
  for i in 1..i_col_objects.count loop
    o_col_objects.extend();
    o_col_objects(i) := col_object(i_col_objects(i).d + i_delta_d + i, i_col_objects(i).ts + i_delta_ts + i, i_col_objects(i).s || ' - ' || DBMS_RANDOM.string('A',10));
  end loop;
end collections_of_objects;
/