alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

create or replace type body base_object
as

member function val return varchar2
is
begin
  return 'd:'||to_char(d, 'yyyy/mm/dd')||'/ts:'||to_char(ts, 'yyyy/mm/dd hh:mi:ss')||'/instance:'||instance||'/db_version:'||db_version||'/db_release:'||db_release; 
end val;

end;
/