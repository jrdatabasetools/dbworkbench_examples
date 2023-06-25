alter session set container = XEPDB1;

create user dbw_examples identified by dbw_examples;

GRANT CREATE SESSION TO dbw_examples;
GRANT ALTER ANY PROCEDURE TO dbw_examples;
GRANT ALTER SESSION TO dbw_examples;
GRANT CREATE ANY PROCEDURE TO dbw_examples;
GRANT CREATE ANY VIEW TO dbw_examples;
GRANT CREATE SYNONYM TO dbw_examples;
GRANT CREATE TABLE TO dbw_examples;
GRANT CREATE VIEW TO dbw_examples;
GRANT DROP ANY PROCEDURE TO dbw_examples;
GRANT UNLIMITED TABLESPACE TO dbw_examples;
grant connect to dbw_examples;
grant create sequence to dbw_examples;
grant create type to dbw_examples;