#Construction of the .jar file

FROM maven:3.8.5-openjdk-17-slim AS build

WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn clean package -DskipTests

#Execution of the .jar file
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/*.jar /app/quality_air_backend.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "quality_air_backend.jar"]
