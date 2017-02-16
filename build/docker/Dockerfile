FROM tomcat:8.0-jre8
MAINTAINER sguydye

COPY /libs/jax-rs-service-0.1.0.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]