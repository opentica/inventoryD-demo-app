#!/bin/sh
#AppDynamics Envirnment variables
APPD_OPTS="-javaagent:/sharedFiles/AppServerAgent/javaagent.jar"
APPD_OPTS="${APPD_OPTS} -Dappdynamics.agent.applicationName=inventoryOsamaDocker"
APPD_OPTS="${APPD_OPTS} -Dappdynamics.agent.tierName=inventory"
APPD_OPTS="${APPD_OPTS} -Dappdynamics.agent.nodeName=docker-node"
#IP address of the ELK APM server
APPD_OPTS="${APPD_OPTS} -javaagent:/sharedFiles/elastic-apm-agent-1.15.0.jar \
     -Delastic.apm.service_name=inventory-application \
     -Delastic.apm.server_url=http://3.21.32.43:8200 \
     -Delastic.apm.secret_token= \
     -Delastic.apm.application_packages=org.example"

java  ${APPD_OPTS} -jar opentica-spring-boot-demo.jar
#java -jar opentica-spring-boot-demo.jar
