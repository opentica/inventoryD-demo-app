#!/bin/sh

echo IS_APPD : ${IS_APPD}

if [ "$IS_APPD" = 1 ]
then
	echo "Starting with AppDynamic configurartion settings"
	APPD_OPTS="-javaagent:/sharedFiles/AppServerAgent/javaagent.jar"
	APPD_OPTS="${APPD_OPTS} -Dappdynamics.agent.applicationName=inventoryOsamaDocker"
	APPD_OPTS="${APPD_OPTS} -Dappdynamics.agent.tierName=customerservice"
	APPD_OPTS="${APPD_OPTS} -Dappdynamics.agent.nodeName=customer-docker-node"
        APPD_OPTS="${APPD_OPTS} -Dappdynamics.controller.hostName=172.31.0.107"
	APPD_OPTS="${APPD_OPTS} -Dappdynamics.controller.port=8090"
	APPD_OPTS="${APPD_OPTS} -Dappdynamics.agent.accountName=customer1"
	APPD_OPTS="${APPD_OPTS} -Dappdynamics.agent.accountAccessKey=581a60c4-a29d-4342-8915-32a63a2d1f83"
	APPD_OPTS="${APPD_OPTS} -Dappdynamics.controller.ssl.enable=false"

	JAVA_OPTS="${JAVA_OPTS} ${APPD_OPTS}"	
else
	export LS_ACCESS_TOKEN=AL4U4cVLqCDzmPmwgiHq8wd4RRPunx1TYu22Pu22y/JHvov7YuyL0uJmsyKF1LXGZQNphiGz3pSg6HqBP28GYY5vxB8D/Os6Jb8JUVNI
        export LS_SERVICE_NAME=customer
        echo "Starting with OTHER APM configurartion settings"
        JAVA_OPTS="${JAVA_OPTS} -javaagent:/sharedFiles/lightstep-opentelemetry-javaagent-0.16.0.jar"
        echo JAVA_OPTS : ${JAVA_OPTS}
fi	 

echo JAVA_OPTS : ${JAVA_OPTS}

java  ${JAVA_OPTS} -jar customerservice.jar
