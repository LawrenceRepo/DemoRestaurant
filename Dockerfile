FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
WORKDIR /app
COPY ${JAR_FILE} my.jar
ENTRYPOINT ["java","-jar","/app/my.jar"]