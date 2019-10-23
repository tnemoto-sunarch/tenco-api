FROM openjdk:8-jdk-alpine

RUN mkdir /app

WORKDIR /app

COPY target/tencoApi-0.0.1-SNAPSHOT.jar tencoApi.jar

EXPOSE 8081

# create
#ENTRYPOINT ["java", "-DDB_INIT=always", "-Dspring.profiles.active=production", "-jar", "tencoApi.jar"]

# default
ENTRYPOINT ["java", "-Dspring.profiles.active=production", "-jar", "tencoApi.jar"]
