alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

/**
 * Table of varchar2(100)
 */
create or replace type varray_of_varchar as varray(5) of varchar2(100);
/