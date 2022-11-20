FROM jboss/wildfly:19.0.0.Final

COPY deployment/firstservice.jks /opt/jboss/wildfly/standalone/configuration/
COPY deployment/standalone.xml /opt/jboss/wildfly/standalone/configuration/
COPY deployment/application-users.properties /opt/jboss/wildfly/standalone/configuration/
COPY deployment/application-roles.properties /opt/jboss/wildfly/standalone/configuration/

COPY target/first-service-soap.war /opt/jboss/wildfly/standalone/deployments/