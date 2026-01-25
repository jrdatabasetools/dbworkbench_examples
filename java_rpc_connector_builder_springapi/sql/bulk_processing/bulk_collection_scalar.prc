create or replace procedure bulk_collection_scalar
(
  i_col_number  in bulk_table_number, 
  i_col_date    in bulk_table_date, 
  i_col_varchar in bulk_table_varchar
)
/**
 * Bulk collection demo with three input parameter of table with scalar element.
 * 
 * @param i_col_number List of number values to insert into table 'bulk_processing_table'.  
 * @param i_col_date List of date values to insert into table 'bulk_processing_table'.  
 * @param i_col_varchar List of varchar2 values to insert into table 'bulk_processing_table'.  
 */
is
begin
  -- process all rows in one command
  forall i in 1..i_col_number.count
    insert into bulk_processing_table
      (n,d,s)
      values
      (i_col_number(i), i_col_date(i), i_col_varchar(i));
end bulk_collection_scalar;