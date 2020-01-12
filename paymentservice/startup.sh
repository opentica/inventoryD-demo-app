#!/bin/sh
#AppDynamics Envirnment variables
APPD_OPTS="-javaagent:/sharedFiles/AppServerAgent/javaagent.jar"
APPD_OPTS="${APPD_OPTS} -Dappdynamics.agent.applicationName=inventoryOsamaDocker"
APPD_OPTS="${APPD_OPTS} -Dappdynamics.agent.tierName=payment"
APPD_OPTS="${APPD_OPTS} -Dappdynamics.agent.nodeName=docker-node"

java  ${APPD_OPTS} -jar opentica-paymentservice-demo.jar
#java -jar opentica-spring-boot-demo.jar
