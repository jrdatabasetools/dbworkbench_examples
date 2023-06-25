alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

/**
 * Table of number
 */
create or replace type bulk_table_number force as table of number;
/