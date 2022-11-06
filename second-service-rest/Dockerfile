FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-alpine-slim

COPY deployment/secondservice.p12 /ssl/
COPY deployment/firstservice.jks /ssl/
COPY deployment/wait-for /usr/bin/

COPY target/second-service-rest.war second-service-rest.war