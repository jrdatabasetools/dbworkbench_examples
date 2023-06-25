docker run --rm --name ora_xe_21c -p 1521:1521 -p 5500:5500 -v ${PWD}/ora_db_startup:/opt/oracle/scripts/startup -e "ORACLE_PWD=oracle" container-registry.oracle.com/database/express:21.3.0-xe
