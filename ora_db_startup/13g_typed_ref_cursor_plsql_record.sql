alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

create or replace package TYPED_REF_CURSOR_PLSQL_RECORD
/**
 * Typed Ref Cursor of PL/SQL RECORD Demo.
 */
as

-- this type of PL/SQL record will be treated as scalar value
type rec_product is record (
  product   varchar2(100)
);

-- this typed ref cursor represents a list of Java strings
type c_product is ref cursor return rec_product;

-- this type of PL/SQL record will create a transfer object
type rec_product_version_status is record (
  product   varchar2(100),
  version   varchar2(100),
  status    varchar2(100)
);

-- this typed ref cursor represents a list of transfer objects
type c_product_version_status is ref cursor return rec_product_version_status;

/**
 * Return a list of installed database products.
 *
 * @return List of installed products.
 */
function get_product return c_product;

/**
 * Return a list of installed database components.
 *
 * @return List of installed components of PL/SQL record type rec_product_version_status.
 */
function get_product_version_status return c_product_version_status;

end TYPED_REF_CURSOR_PLSQL_RECORD;
/