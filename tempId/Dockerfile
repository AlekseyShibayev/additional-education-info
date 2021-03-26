FROM openjdk:11-jre-slim-buster

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/application.jar"]

#docker build --tag "bot:0" .
#docker run -p 8080:8080 bot:0
