#!/bin/sh
echo IS_APPD : ${IS_APPD}

if [ "$IS_APPD" = 1 ]
then
	echo "Starting with AppDynamic configurartion settings"
	JAVA_OPTS="-javaagent:/sharedFiles/AppServerAgent/javaagent.jar"
	JAVA_OPTS="${JAVA_OPTS} -Dappdynamics.agent.applicationName=inventoryOsamaDocker"
	JAVA_OPTS="${JAVA_OPTS} -Dappdynamics.agent.tierName=productservice"
	JAVA_OPTS="${JAVA_OPTS} -Dappdynamics.agent.nodeName=docker-node"	
else
	#IP address of the ELK APM server
	echo "Starting with ELK APM configurartion settings"
	JAVA_OPTS="${JAVA_OPTS} -javaagent:/sharedFiles/elastic-apm-agent-1.15.0.jar \
     -Delastic.apm.service_name=productservice-application \
     -Delastic.apm.server_url=http://3.21.32.43:8200 \
     -Delastic.apm.secret_token= \
     -Delastic.apm.application_packages=com.optimiz"
fi	 

echo JAVA_OPTS : ${JAVA_OPTS}

java  ${JAVA_OPTS} -jar productservice.jar

