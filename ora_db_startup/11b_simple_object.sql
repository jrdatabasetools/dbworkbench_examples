alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

create or replace type body simple_object
as
  constructor function simple_object(i_diff number)
    return self as result
  as
  begin
    self.d := sysdate + i_diff;
    self.ts := systimestamp - i_diff;
    select sys_context('USERENV','INSTANCE_NAME')
      into self.instance
      from dual;
    self.db_version := DBMS_DB_VERSION.VERSION;
    self.db_release := DBMS_DB_VERSION.RELEASE;
    return;
  end;
end;
/