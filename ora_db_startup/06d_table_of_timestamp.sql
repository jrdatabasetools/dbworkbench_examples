alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

/**
 * Table of timestamp
 */
create or replace type table_of_timestamp as table of timestamp;
/