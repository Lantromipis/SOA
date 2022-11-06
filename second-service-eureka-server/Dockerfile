FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-alpine-slim

COPY target/second-service-eureka-server.war second-service-eureka-server.war

ENTRYPOINT ["java","-jar","/second-service-eureka-server.war"]