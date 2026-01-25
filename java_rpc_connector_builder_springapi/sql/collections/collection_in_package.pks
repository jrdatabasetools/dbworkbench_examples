create or replace package collection_in_package
as
/**
 * Demonstration of calling in-package defined collections.
 */

type number_table_in_package is table of number(9);
type timestamp_table_in_package is table of timestamp;
type varchar_varray_in_package is varray(5) of varchar2(100);

/**
 * Demonstration of calling a stored procedure with in/put parameter of collections of scalar element types.
 *
 * @param i_col_number Table-Collection of integer input values.
 * @param io_col_timestamp Table-Collection of timestamp input/output values.
 * @param o_col_varchar Varray(5)-Collection of varchar2 output values.
 */
procedure do_it
(
  i_col_number      in     number_table_in_package,
  io_col_timestamp  in out timestamp_table_in_package,
  o_col_varchar     out    varchar_varray_in_package
);

end collection_in_package;