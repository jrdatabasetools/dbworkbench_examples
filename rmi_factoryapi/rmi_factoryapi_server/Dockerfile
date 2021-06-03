FROM ringcentral/maven:3.6.3-jdk11.0.7
WORKDIR /app
COPY target/rmi_factoryapi_server-jar-with-dependencies.jar /app
EXPOSE 1098 1099
CMD ["java", "-Djava.rmi.server.hostname=0.0.0.0", "-jar", "rmi_factoryapi_server-jar-with-dependencies.jar"]
