FROM openjdk:8

#COPY application jar file
ADD target/productservice-0.0.1-SNAPSHOT.jar productservice.jar

#EXTRACT APPD JAVA AGENT
#RUN mkdir -p /sharedFiles/AppServerAgent
#COPY AppServerAgent.zip /sharedFiles/AppServerAgent.zip
#RUN unzip /sharedFiles/AppServerAgent.zip -d /sharedFiles/AppServerAgent/

#COPY the Elastic APM agent
#COPY elastic-apm-agent-1.15.0.jar /sharedFiles/elastic-apm-agent-1.15.0.jar
COPY lightstep-opentelemetry-javaagent-0.16.0.jar /sharedFiles/lightstep-opentelemetry-javaagent-0.16.0.jar
CMD ["tail","-f","/dev/null"]

#COPY STARTUP script
COPY startup.sh /usr/local/bin/startup.sh
RUN chmod +x /usr/local/bin/startup.sh

EXPOSE 8080
ENTRYPOINT ["startup.sh","run"]
