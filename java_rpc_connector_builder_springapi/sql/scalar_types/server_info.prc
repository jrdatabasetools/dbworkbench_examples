create or replace procedure server_info
(
  i_diff        in   number,
  o_date        out  date,
  o_timestamp   out  timestamp,
  o_instance    out  varchar2,
  o_db_version  out  number,
  o_db_release  out  number
)
/**
 * Database server information.
 *
 * @param i_diff Offset of date and timestamp value.
 * @param o_date System date plus i_diff offset.
 * @param o_timestamp System timestamp plus i_diff offset.
 * @param o_instance Instance information.
 * @param o_db_version Oracle version.
 * @param o_db_release Oracle release.
 */
is
begin
  o_date := sysdate + i_diff;
  o_timestamp := systimestamp - i_diff;
  select sys_context('USERENV','INSTANCE_NAME')
    into o_instance
    from dual;
  o_db_version := DBMS_DB_VERSION.VERSION;
  o_db_release := DBMS_DB_VERSION.RELEASE;
end server_info;