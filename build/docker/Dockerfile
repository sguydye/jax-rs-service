FROM tomcat:9.0.0.M17-jre8-alpine
MAINTAINER sguydye

COPY /libs/jax-rs-service.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]