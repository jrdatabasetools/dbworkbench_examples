create or replace package body TYPED_REF_CURSOR_OBJECT
as

/**
 * Return a list of objects/transfer objects.
 *
 * @return List of objects/transfer objects.
 */
function get_refcur_object(i_number_of_rows in number) return c_refcur_object
is
  c c_refcur_object;
begin
  open c for
    select  refcur_object(rownum, 'element refcur_object no. ' || rownum)
      from  dual 
      connect by level<=i_number_of_rows;

  return c;
end get_refcur_object;

/**
 * Return a list of pl/sql records/transfer objects including an object.
 *
 * @return List of pl/sql records/transfer objects including an object.
 */
function get_refcur_object_mixed(i_number_of_rows in number) return c_refcur_object_mixed
is
  c c_refcur_object_mixed;
begin
  open c for
    select  systimestamp+rownum * 12.3456,
            refcur_object(rownum, 'element refcur_object no. ' || rownum)
      from  dual 
      connect by level<=i_number_of_rows;

  return c;
end get_refcur_object_mixed;

end TYPED_REF_CURSOR_OBJECT;