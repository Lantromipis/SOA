FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-alpine-slim

COPY deployment/secondservice.p12 /ssl/
COPY deployment/firstservice.jks /ssl/

COPY target/second-service.war second-service.war
ENTRYPOINT ["java","-jar","/second-service.war"]