create or replace package collection_of_rowtype
as
/**
 * Demonstration of using collections of rowtype elements in combination of bulk processing.
 */

type table_rowtype is table of bulk_processing_table%rowtype;

/**
 * Demonstration of calling a stored procedure with input of table of 'BULK_PROCESSING_TABLE%ROWTYPE' elements. 
 * The elements are written into table 'BULK_PROCESSING_TABLE' and read from the table 'BULK_PROCESSING_TABLE'.
 *
 * @param i_table_rowtype Input list of 'bulk_processing_table%rowtype' elements.
 * @param o_table_rowtype Output list of 'bulk_processing_table%rowtype' elements.
 */
procedure doit
(
  i_table_rowtype  in    table_rowtype,
  o_table_rowtype  out   table_rowtype
);
  
end collection_of_rowtype;