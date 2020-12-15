FROM openjdk:8

#COPY application jar file
ADD target/paymentservice-0.0.1-SNAPSHOT.jar opentica-paymentservice-demo.jar


CMD ["tail","-f","/dev/null"]

#COPY STARTUP script
COPY startup.sh /usr/local/bin/startup.sh
RUN chmod +x /usr/local/bin/startup.sh

EXPOSE 8080
ENTRYPOINT ["startup.sh","run"]
