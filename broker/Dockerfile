FROM openjdk:8-jre-alpine
COPY target/*-allinone.jar /.jar
EXPOSE 1099
CMD ["/usr/bin/java", "-jar", "/.jar"]