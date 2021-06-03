/**
 * Type object simple_object with one constructor.
 */
create or replace type simple_object force as object (
  d           date,
  ts          timestamp,
  instance    varchar2(100),
  db_version  number(9),
  db_release  number(9),
  constructor function simple_object(i_diff number) return self as result  
);