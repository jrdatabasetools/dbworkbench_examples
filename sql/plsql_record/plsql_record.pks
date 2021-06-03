create or replace package plsql_record
as
/**
 * PL/SQL record Demo.
 */

-- this type of PL/SQL record will create a transfer object
type demo_record is record (
  d         date,
  ts        timestamp,
  n         number(9),
  f         number(12,3),
  s         varchar2(100)
);

/**
 * Input and output parameter of type PL/SQL record.
 *
 * @param io_rec Input parameter record value is modified and returned.
 */
procedure modify_record(io_rec in out demo_record);

end plsql_record;