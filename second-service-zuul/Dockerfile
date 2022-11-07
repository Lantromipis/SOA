FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-alpine-slim

COPY target/second-service-zuul.war second-service-zuul.war

ENTRYPOINT ["java","-jar","/second-service-zuul.war"]