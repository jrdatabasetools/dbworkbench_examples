create or replace package TYPED_REF_CURSOR_COLLECTION
/**
 * Typed Ref Cursor of Collection Demo.
 */
as

-- this type of PL/SQL record will be treated as list of object/transfer object value
type rec_refcur_collection is record (
  o   refcur_collection
);

-- this typed ref cursor represents a list of list of object/transfer object values
type c_refcur_collection is ref cursor return rec_refcur_collection;

-- this type of PL/SQL record will be treated as object/transfer object value
type rec_refcur_collection_mixed is record (
  ts            timestamp,
  row_number_id number,
  refcur_list   refcur_collection
);

-- this typed ref cursor represents a list of transfer objects containing a list element
type c_refcur_collection_mixed is ref cursor return rec_refcur_collection_mixed;

/**
 * Return a list of list of objects/transfer objects.
 *
 * @return List of list of objects/transfer objects.
 */
function get_refcur_collection(i_number_of_rows in number) return c_refcur_collection;

/**
 * Return a list of pl/sql records/transfer objects including a list of objects.
 *
 * @return List of pl/sql records/transfer objects including a list of objects.
 */
function get_refcur_collection_mixed(i_number_of_rows in number) return c_refcur_collection_mixed;

end TYPED_REF_CURSOR_COLLECTION;