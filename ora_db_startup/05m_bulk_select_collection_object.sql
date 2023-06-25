alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

create or replace function bulk_select_collection_object(i_limit in number)
return bulk_table_object
/**
 * Demonstration of fetching table object elements.
 * 
 * @param i_limit Maximum number of elements to read.
 * @return Collection of bulk_object fetched by bulk collect.  
 */
is
  v_bulk_table_object bulk_table_object;

  -- the cursor returns elements of bulk_objects
  cursor c_bulk_processing_table is
    select bulk_object(n, d, s)
      from bulk_processing_table;
begin
  -- open the cursor
  open c_bulk_processing_table;
  
  -- fetch elements
  fetch c_bulk_processing_table
    bulk collect into v_bulk_table_object
    limit i_limit;
    
  -- close the cursor 
  close c_bulk_processing_table;
  
  return v_bulk_table_object;
end bulk_select_collection_object;
/