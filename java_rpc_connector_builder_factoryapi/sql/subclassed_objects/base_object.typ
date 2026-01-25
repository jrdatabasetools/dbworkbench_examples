/**
 * Parent object containing one member function for inspect information.
 */
create or replace type base_object force as object (
  d           date,
  ts          timestamp,
  instance    varchar2(100),
  db_version  number(9),
  db_release  number(9),
  member function val return varchar2  
) not final;