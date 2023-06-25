alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

/**
 * Table of object bulk_object
 */
create or replace type bulk_table_object force as table of bulk_object;
/