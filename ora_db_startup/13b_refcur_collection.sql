alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

/**
 * REFCUR_COLLECTION for typed ref cursor examples.
 */
create or replace type refcur_collection force as table of refcur_object;
/