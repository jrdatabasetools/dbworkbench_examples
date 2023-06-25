alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

create or replace package body subclassed_objects
as

function get_base_object return base_object
is
  o base_object;
begin
  o := base_object(null, null, null, null, null);
  o.d := sysdate;
  o.ts := systimestamp;
  select sys_context('USERENV','INSTANCE_NAME')
    into o.instance
    from dual;
  o.db_version := DBMS_DB_VERSION.VERSION;
  o.db_release := DBMS_DB_VERSION.RELEASE;
  return o;
end get_base_object;

function get_derived_object(i_diff in number) return base_object
is
  o base_object;
begin
  select derived_object(sysdate, 
                        systimestamp, 
                        sys_context('USERENV','INSTANCE_NAME'), 
                        DBMS_DB_VERSION.VERSION, 
                        DBMS_DB_VERSION.RELEASE, 
                        dbms_random.string('A', 100), 
                        systimestamp+i_diff, 
                        sysdate-i_diff, 
                        DBMS_RANDOM.value(0,999999999))
    into o
    from dual;  
  return o; 
end get_derived_object;

function inspect_object(i_object in base_object) return varchar2
is
  result varchar2(1000);
begin
  if (i_object is of(only base_object)) then
    result := 'base_object ['|| i_object.val ||']';
  elsif (i_object is of(only derived_object)) then
    result := 'derived_object ['|| i_object.val ||']';
  else 
    result := 'null or unknown object';
  end if;
  
  return result;
end inspect_object;

end subclassed_objects;
/