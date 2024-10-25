FROM maven:3.8.5-openjdk-17-slim

WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn clean package -DskipTests
RUN mv /app/target/*.jar /app/target/quality_air_backend.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/target/quality_air_backend.jar"]

