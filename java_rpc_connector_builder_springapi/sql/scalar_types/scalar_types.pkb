create or replace package body scalar_types
as

function add_num(i_n1 in number, i_n2 in number) return number
is
begin
  return i_n1 + i_n2;
end add_num;

function concat_char(i_s1 in varchar2, i_s2 in varchar2) return varchar2
is
begin
  return i_s1 || i_s2;
end concat_char;

end scalar_types;