#!/bin/sh

echo "Starting with AppDynamic configurartion settings"
APPD_OPTS="-javaagent:/sharedFiles/AppServerAgent/javaagent.jar"
APPD_OPTS="${APPD_OPTS} -Dappdynamics.agent.applicationName=inventoryOsamaDocker"
APPD_OPTS="${APPD_OPTS} -Dappdynamics.agent.tierName=paymentservice"
APPD_OPTS="${APPD_OPTS} -Dappdynamics.agent.nodeName=customer-docker-node"
APPD_OPTS="${APPD_OPTS} -Dappdynamics.controller.hostName=172.31.0.107"
APPD_OPTS="${APPD_OPTS} -Dappdynamics.controller.port=8090"
APPD_OPTS="${APPD_OPTS} -Dappdynamics.agent.accountName=customer1"
APPD_OPTS="${APPD_OPTS} -Dappdynamics.agent.accountAccessKey=581a60c4-a29d-4342-8915-32a63a2d1f83"
APPD_OPTS="${APPD_OPTS} -Dappdynamics.controller.ssl.enable=false"

JAVA_OPTS="${JAVA_OPTS} ${APPD_OPTS}"	

echo JAVA_OPTS : ${JAVA_OPTS}

java  -jar opentica-paymentservice-demo.jar
