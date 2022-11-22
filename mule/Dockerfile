FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-alpine-slim

COPY mule-standalone /mule/
COPY  target/mule-1.0.0-SNAPSHOT-mule-application.jar /mule/apps/

CMD [ "/mule/bin/mule"]