FROM container-registry.oracle.com/database/express:21.3.0-xe
ARG ORACLE_PWD=oracle
COPY ora_db_startup /opt/oracle/scripts/startup
EXPOSE 1521 5500
