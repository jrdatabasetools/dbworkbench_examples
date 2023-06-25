alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

/**
 * Object type with three fields of number, date and varchar2.
 */
create or replace type bulk_object force as object (
  n   number,
  d   date,
  s   varchar2(100)
);
/