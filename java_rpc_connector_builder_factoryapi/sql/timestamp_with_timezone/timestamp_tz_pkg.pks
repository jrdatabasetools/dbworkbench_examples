create or replace package timestamp_tz_pkg
as

function fnc_date(i_date in varchar2, i_format in varchar2) return date;
function fnc_date_null return date;
function fnc_timestamp(i_timestamp in varchar2, i_format in varchar2) return timestamp;
function fnc_timestamp_null return timestamp;

function fnc_timestamp_wtz(i_timestamp in varchar2, i_format in varchar2) return timestamp with time zone;
function fnc_timestamp_wtz_null return timestamp with time zone;
function fnc_timestamp_wltz(i_timestamp in varchar2, i_format in varchar2) return timestamp with local time zone;
function fnc_timestamp_wltz_null return timestamp with local time zone;

procedure prc_sysdate(i_date in date, i_format in varchar2, o_sysdate out varchar2);
procedure prc_timestamp(i_timestamp in timestamp, i_format in varchar2, o_timestamp out varchar2);
procedure prc_timestamp_wtz(i_timestamp_wtz in timestamp with time zone, i_format in varchar2, o_timestamp_wtz out varchar2);
procedure prc_timestamp_wltz(i_timestamp_wltz in timestamp with local time zone, i_format in varchar2, o_timestamp_wltz out varchar2);

end timestamp_tz_pkg;