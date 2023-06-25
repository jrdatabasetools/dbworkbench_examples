alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

/**
 * REFCUR_OBJECT for typed ref cursor examples.
 */
create or replace type refcur_object force as object (
  n           number(9),
  v           varchar2(100)
);
/