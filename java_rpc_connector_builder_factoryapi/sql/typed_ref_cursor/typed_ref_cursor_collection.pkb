create or replace package body TYPED_REF_CURSOR_COLLECTION
as

/**
 * Return a list of list of objects/transfer objects.
 *
 * @return List of list of objects/transfer objects.
 */
function get_refcur_collection(i_number_of_rows in number) return c_refcur_collection
is
  c c_refcur_collection;
begin
  open c for
    with row_number_id as 
      (select rownum id from dual connect by level<=i_number_of_rows)
    select  cast(multiset(select refcur_object(row_number_id.id, 'element refcur_object no. ' || rownum)
                            from dual
                            connect by level<=i_number_of_rows) as refcur_collection)
      from  row_number_id;

  return c;
end get_refcur_collection;

/**
 * Return a list of pl/sql records/transfer objects including a list of objects.
 *
 * @return List of pl/sql records/transfer objects including a list of objects.
 */
function get_refcur_collection_mixed(i_number_of_rows in number) return c_refcur_collection_mixed
is
  c c_refcur_collection_mixed;
begin
  open c for
    with row_number_id as 
      (select rownum id, (systimestamp+rownum*123.456) ts from dual connect by level<=i_number_of_rows)
    select  row_number_id.ts,
            row_number_id.id,
            cast(multiset(select refcur_object(row_number_id.id, 'element refcur_object no. ' || rownum)
                            from dual
                            connect by level<=i_number_of_rows) as refcur_collection)
      from  row_number_id;

  return c;
end get_refcur_collection_mixed;

end TYPED_REF_CURSOR_COLLECTION;