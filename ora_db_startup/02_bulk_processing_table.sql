alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

create table bulk_processing_table
(
n    number not null,
d    date not null,
s    varchar2(100) not null
);
