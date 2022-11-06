FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-alpine-slim

COPY deployment/properties/ /usr/share/spring-config/
COPY target/second-service-config-server.war second-service-config-server.war

ENTRYPOINT ["java","-jar","/second-service-config-server.war"]