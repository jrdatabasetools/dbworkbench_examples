create or replace function server_info_simple_object
(
  i_diff 			in	 	number
)
return simple_object
is
begin
  return simple_object(i_diff);
end server_info_simple_object;