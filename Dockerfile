# 164 mb
FROM bellsoft/liberica-openjdk-alpine-musl

# 188 mb
#FROM bellsoft/liberica-openjdk-alpine

# не собирает
#FROM openjdk11:alpine-jre

# 208 mb
#FROM adoptopenjdk/openjdk11:alpine-jre

# 199 mb
#FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.5_10

# 209 mb
#FROM adoptopenjdk/openjdk11:jre-11.0.9_11.1-alpine

# 277.8 mb
#FROM openjdk:11-jre-slim-buster

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
EXPOSE 8080

# debug
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,address=*:5005,server=y,suspend=n
EXPOSE 5005

ENTRYPOINT ["java", "-jar", "/application.jar"]