create or replace package body rowtype_demo
as

procedure modify_record(io_rowtype_example in out rowtype_example%rowtype)
is
begin
  io_rowtype_example.n  := io_rowtype_example.n + 1;
  io_rowtype_example.f  := io_rowtype_example.f + 1;
  io_rowtype_example.ts := io_rowtype_example.ts + 1;
  io_rowtype_example.d  := io_rowtype_example.d + 1;
  io_rowtype_example.v  := io_rowtype_example.v || '1';
end modify_record;

end rowtype_demo;