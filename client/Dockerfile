FROM openjdk:8-jre-alpine
COPY target/*-allinone.jar /.jar
EXPOSE 1099
CMD /bin/sh -c "sleep 4 && java -jar /.jar"