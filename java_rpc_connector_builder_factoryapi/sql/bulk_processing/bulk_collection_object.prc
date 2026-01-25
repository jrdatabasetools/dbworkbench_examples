create or replace procedure bulk_collection_object(i_col_object in bulk_table_object)
/**
 * Bulk collection demo with input parameter as a collection of object.
 * 
 * @param i_col_object List of object type values to insert into table 'bulk_processing_table'.  
 */
is
begin
  forall i in 1..i_col_object.count
    insert into bulk_processing_table
      (n,d,s)
      values
      (i_col_object(i).n, i_col_object(i).d, i_col_object(i).s);
end bulk_collection_object;
