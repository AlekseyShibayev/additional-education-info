FROM openjdk:11-jre-slim-buster

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
EXPOSE 8080

# debug
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,address=*:5005,server=y,suspend=n
EXPOSE 5005

ENTRYPOINT ["java", "-jar", "/application.jar"]

#docker build --tag "bot:0" .
#docker run -p 8080:8080 -p 5005:5005 bot:0

# default for postman test is:
#   http://localhost:8080
#   http://127.0.0.1:8080