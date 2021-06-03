create or replace package TYPED_REF_CURSOR_ROWTYPE
/**
 * Typed Ref Cursor of ROWTYPE Demo.
 */
as

-- this typed ref cursor represents a list of transfer objects
type c_rowtype_example is ref cursor return rowtype_example%rowtype;

/**
 * Return a list of rowtype elements.
 *
 * @return List of rowtype elements.
 */
function get_rowtype_example(i_number_of_rows in number) return c_rowtype_example;

end TYPED_REF_CURSOR_ROWTYPE;