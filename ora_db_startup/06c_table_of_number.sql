alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

/**
 * Table of number
 */
create or replace type table_of_number as table of number(9);
/