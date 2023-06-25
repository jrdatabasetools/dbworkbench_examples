alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

/**
 * Table of type col_object. 
 */
create or replace type table_of_col_object as table of col_object;
/