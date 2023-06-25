alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

create or replace package TYPED_REF_CURSOR_OBJECT
/**
 * Typed Ref Cursor of Object Demo.
 */
as

-- this type of PL/SQL record will be treated as object/transfer object value
type rec_refcur_object is record (
  o   refcur_object
);

-- this typed ref cursor represents a list of object/transfer object values
type c_refcur_object is ref cursor return rec_refcur_object;

-- this type of PL/SQL record will be treated as object/transfer object value
type rec_refcur_object_mixed is record (
  ts  timestamp,
  o   refcur_object
);

-- this typed ref cursor represents a list of transfer objects
type c_refcur_object_mixed is ref cursor return rec_refcur_object_mixed;

/**
 * Return a list of objects/transfer objects.
 *
 * @return List of objects/transfer objects.
 */
function get_refcur_object(i_number_of_rows in number) return c_refcur_object;

/**
 * Return a list of pl/sql records/transfer objects including an object.
 *
 * @return List of pl/sql records/transfer objects including an object.
 */
function get_refcur_object_mixed(i_number_of_rows in number) return c_refcur_object_mixed;

end TYPED_REF_CURSOR_OBJECT;
/