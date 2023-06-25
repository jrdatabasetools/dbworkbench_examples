alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

create or replace package body plsql_record
as

procedure modify_record(io_rec in out demo_record)
is
begin
  io_rec.d  := io_rec.d + 1;
  io_rec.ts := io_rec.ts + 1;
  io_rec.n  := io_rec.n + 1;
  io_rec.f  := io_rec.f + 1;
  io_rec.s  := io_rec.s || '1';
end modify_record;

end plsql_record;
/