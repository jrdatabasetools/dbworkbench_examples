alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

create or replace package body timestamp_tz_pkg
as

function fnc_date(i_date in varchar2, i_format in varchar2) return date
is
begin
  return to_date(i_date, i_format);
end fnc_date;


function fnc_date_null return date
is
begin
  return null;
end fnc_date_null;


function fnc_timestamp(i_timestamp in varchar2, i_format in varchar2) return timestamp
is
begin
  return to_timestamp(i_timestamp, i_format);
end fnc_timestamp;


function fnc_timestamp_null return timestamp
is
begin
  return null;
end fnc_timestamp_null;


function fnc_timestamp_wtz(i_timestamp in varchar2, i_format in varchar2) return timestamp with time zone
is
begin
  return to_timestamp_tz(i_timestamp, i_format);
end fnc_timestamp_wtz;


function fnc_timestamp_wtz_null return timestamp with time zone
is
begin
  return null;
end fnc_timestamp_wtz_null;


function fnc_timestamp_wltz(i_timestamp in varchar2, i_format in varchar2) return timestamp with local time zone
is
begin
  return to_timestamp_tz(i_timestamp, i_format);
end fnc_timestamp_wltz;


function fnc_timestamp_wltz_null return timestamp with local time zone
is
begin
  return null;
end fnc_timestamp_wltz_null;


procedure prc_sysdate(i_date in date, i_format in varchar2, o_sysdate out varchar2)
is
begin
  o_sysdate := to_char(i_date, i_format);
end prc_sysdate;


procedure prc_timestamp(i_timestamp in timestamp, i_format in varchar2, o_timestamp out varchar2)
is
begin
  o_timestamp := to_char(i_timestamp, i_format);
end prc_timestamp;


procedure prc_timestamp_wtz(i_timestamp_wtz in timestamp with time zone, i_format in varchar2, o_timestamp_wtz out varchar2)
is
begin
  o_timestamp_wtz := to_char(i_timestamp_wtz, i_format);
end prc_timestamp_wtz;


procedure prc_timestamp_wltz(i_timestamp_wltz in timestamp with local time zone, i_format in varchar2, o_timestamp_wltz out varchar2)
is
begin
  o_timestamp_wltz := to_char(i_timestamp_wltz, i_format);
end prc_timestamp_wltz;


end timestamp_tz_pkg;
/