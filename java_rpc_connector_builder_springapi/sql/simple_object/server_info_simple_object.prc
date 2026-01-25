create or replace function server_info_simple_object
(
  i_diff 			in	 	number
)
return simple_object
/**
 * Get the database server information as object type.
 *
 * @param i_diff Offset for date values.
 * @return Server info as object type simple_object.
 */
is
begin
  return simple_object(i_diff);
end server_info_simple_object;