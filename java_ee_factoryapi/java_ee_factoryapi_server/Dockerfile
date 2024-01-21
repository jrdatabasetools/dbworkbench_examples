FROM jboss/wildfly:24.0.0.Final

# client http remoting configuration does not work after 24.0.0.final  
#FROM jboss/wildfly:25.0.0.Final
#FROM quay.io/wildfly/wildfly:30.0.0.Final-jdk11

# user configuration : user dbworkbench100 is configured for remote access (see jave_ee_factoryapi_client/.../wildfly-config.xml)
RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent
RUN /opt/jboss/wildfly/bin/add-user.sh -a -u dbworkbench100 -p dbworkbench100 --silent

# wildfly configuration (datasource dbw_examples preconfigured : url, username and password set via docker run call from client pom.xml)
COPY wildfly_configuration/standalone.xml /opt/jboss/wildfly/standalone/configuration

# oracle jdbc driver configuration
COPY wildfly_configuration/module.xml /opt/jboss/wildfly/modules/system/layers/base/com/oracle/jdbc/main/
COPY target/oracle_jdbc_driver/ojdbc11.jar /opt/jboss/wildfly/modules/system/layers/base/com/oracle/jdbc/main/
COPY target/oracle_jdbc_driver/ucp11.jar /opt/jboss/wildfly/modules/system/layers/base/com/oracle/jdbc/main/

# copy application to deployments
COPY target/java_ee_factoryapi_server.jar /opt/jboss/wildfly/standalone/deployments
EXPOSE 8080 9990

# run command
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
