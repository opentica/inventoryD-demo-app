#!/bin/sh
#AppDynamics Envirnment variables
APPD_OPTS="-javaagent:/sharedFiles/AppServerAgent/javaagent.jar"
APPD_OPTS="${APPD_OPTS} -Dappdynamics.agent.applicationName=inventoryOsamaDocker"
APPD_OPTS="${APPD_OPTS} -Dappdynamics.agent.tierName=purchase"
APPD_OPTS="${APPD_OPTS} -Dappdynamics.agent.nodeName=docker-node"
APPD_OPTS="${APPD_OPTS} -javaagent:/sharedFiles/elastic-apm-agent-1.15.0.jar \
     -Delastic.apm.service_name=purchaseservice-application \
     -Delastic.apm.server_url=http://3.21.32.43:8200 \
     -Delastic.apm.secret_token= \
     -Delastic.apm.application_packages=com.optimiz"

java  ${APPD_OPTS} -jar opentica-purchaseservice-demo.jar
#java -jar opentica-spring-boot-demo.jar
