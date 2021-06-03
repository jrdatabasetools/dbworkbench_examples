create or replace package body TYPED_REF_CURSOR_ROWTYPE
as

/**
 * Return a list of rowtype elements.
 *
 * @return List of rowtype elements.
 */
function get_rowtype_example(i_number_of_rows in number) return c_rowtype_example
is
  c c_rowtype_example;
begin
  open c for 
    select rownum,
           rownum * 1.234,
           systimestamp + rownum * 12.3456,
           sysdate + rownum * 17,
           to_char(systimestamp+rownum*2.3456) || ' -> ' || rownum
      from  dual 
      connect by level<=i_number_of_rows;
      
  return c;
end get_rowtype_example;

end TYPED_REF_CURSOR_ROWTYPE;