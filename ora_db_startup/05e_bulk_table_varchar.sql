alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

/**
 * Table of varchar2
 */
create or replace type bulk_table_varchar force as table of varchar2(100);
/