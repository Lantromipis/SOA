FROM jboss/wildfly:19.0.0.Final

COPY deployment/standalone.xml /opt/jboss/wildfly/standalone/configuration/
COPY deployment/application-users.properties /opt/jboss/wildfly/standalone/configuration/
COPY deployment/application-roles.properties /opt/jboss/wildfly/standalone/configuration/

COPY target/first-service-ejb.war /opt/jboss/wildfly/standalone/deployments/
