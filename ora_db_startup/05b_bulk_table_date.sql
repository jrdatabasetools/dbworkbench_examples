alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

/**
 * Table of date
 */
create or replace type bulk_table_date force as table of date;
/