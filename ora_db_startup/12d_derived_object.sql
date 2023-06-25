alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

create or replace type body derived_object
as

overriding member function val return varchar2
is
begin
  return (self as base_object).val || '/new_s:'||new_s||'/new_ts:'||to_char(new_ts, 'yyyy/mm/dd hh:mi:ss')||'/new_d:'||to_char(new_d, 'yyyy/mm/dd')||'/new_n:'||new_n; 
end val;

end;
/