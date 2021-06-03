create or replace package body TYPED_REF_CURSOR_PLSQL_RECORD
as

/**
 * Return a list of installed database products.
 *
 * @return List of installed products.
 */
function get_product return c_product
is
  c c_product;
begin
  open c for
    select  trim(product)
      from  product_component_version;
      
  return c;
end get_product;

/**
 * Return a list of installed database components.
 *
 * @return List of installed components of PL/SQL record type rec_product_version_status.
 */
function get_product_version_status return c_product_version_status
is
  c c_product_version_status;
begin
  open c for
    select  trim(product),
            trim(version),
            trim(status)
      from  product_component_version;
      
  return c;
end get_product_version_status;

end TYPED_REF_CURSOR_PLSQL_RECORD;