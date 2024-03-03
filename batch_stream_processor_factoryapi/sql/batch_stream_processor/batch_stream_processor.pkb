create or replace package body batch_stream_processor
as

function job1 (i_number_of_rows in number) return c_batch_row
is
  c c_batch_row;
begin
  -- generate dummy list of rows
  open c for
    select  level, 
            'any description no. ' || level, 
            systimestamp+level+level/12. 
      from  dual 
      connect by level<=i_number_of_rows;

  -- return cursor
  return c;
end job1;

function a return number
is
begin
  return 1;
end ;

end batch_stream_processor;