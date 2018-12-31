FROM openjdk:8

#COPY application jar file
ADD target/opentica-spring-boot-demo.jar opentica-spring-boot-demo.jar

#EXTRACT APPD JAVA AGENT
RUN mkdir -p /sharedFiles/AppServerAgent
COPY AppServerAgent.zip /sharedFiles/AppServerAgent.zip
RUN unzip /sharedFiles/AppServerAgent.zip -d /sharedFiles/AppServerAgent/

CMD ["tail","-f","/dev/null"]

#COPY STARTUP script
COPY startup.sh /usr/local/bin/startup.sh
RUN chmod +x /usr/local/bin/startup.sh

EXPOSE 8080
ENTRYPOINT ["startup.sh","run"]
