create or replace package body collection_of_rowtype
as

/**
 * Demonstration of calling a stored procedure with input of table of 'BULK_PROCESSING_TABLE%ROWTYPE' elements. 
 * The input elements are written into table 'BULK_PROCESSING_TABLE' and the read result from the table 'BULK_PROCESSING_TABLE' is returned.
 *
 * @param i_table_rowtype Input list of 'bulk_processing_table%rowtype' elements.
 * @param o_table_rowtype Output list of 'bulk_processing_table%rowtype' elements.
 */
procedure doit
(
  i_table_rowtype  in    table_rowtype,
  o_table_rowtype  out   table_rowtype
)
is
begin
  -- write elements 
  forall i in 1..i_table_rowtype.count
    insert into bulk_processing_table
      values i_table_rowtype(i);

  -- fetch elements (better solution:fetch cursor ... bulk collect ... limit ...)
  select * 
    bulk collect
    into o_table_rowtype
    from bulk_processing_table
    where rownum <= 10000;
end doit;  


end collection_of_rowtype;