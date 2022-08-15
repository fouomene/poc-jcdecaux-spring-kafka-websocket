FROM openjdk:11
MAINTAINER fouomene.com
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} consumer-station.jar
ENTRYPOINT ["java","-jar","/consumer-station.jar"]