alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

/**
 * Derived object containing overriding member function for inspect information.
 */
create or replace type derived_object force under base_object (
  new_s    varchar2(100),
  new_ts   timestamp,
  new_d    date,
  new_n    number(9),
  overriding member function val return varchar2
);
/