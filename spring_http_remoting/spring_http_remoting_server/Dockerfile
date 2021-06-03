FROM ringcentral/maven:3.6.3-jdk11.0.7
WORKDIR /app
COPY target/spring_http_remoting_server.war /app
EXPOSE 8080
CMD ["java", "-jar", "spring_http_remoting_server.war"]
