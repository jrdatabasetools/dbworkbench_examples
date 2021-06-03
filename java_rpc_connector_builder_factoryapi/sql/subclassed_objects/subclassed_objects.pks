create or replace package subclassed_objects
/**
 * Demonstration of calling a stored procedure with in/put parameter of collections of scalar element types.
 *
 * @param i_col_number Table-Collection of integer input values.
 * @param io_col_timestamp Table-Collection of timestamp value.
 * @param o_col_varchar Varray(5)-Collection of varchar2 out put values.
 */
as

/**
 * Generating an object of type base_object.
 *
 * @return Object of type base_object.
 */
function get_base_object return base_object;

/**
 * Generating an object of type derived_object.
 *
 * @param i_diff Offset of field values of type derived_object.
 * @return Object of type derived_object.
 */
function get_derived_object(i_diff in number) return base_object;

/**
 * Calling member function 'val' of base_object or derived_object.
 *
 * @param base_object Object value to inspect.
 * @return Result of member function 'val'.
 */
function inspect_object(i_object in base_object) return varchar2;

end subclassed_objects;