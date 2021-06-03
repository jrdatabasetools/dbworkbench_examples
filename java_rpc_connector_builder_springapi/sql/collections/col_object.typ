/**
 * Object type with three fields of date, timestamp and varchar2.
 */
create or replace type col_object force as object (
  d           date,
  ts          timestamp,
  s           varchar2(100)
);