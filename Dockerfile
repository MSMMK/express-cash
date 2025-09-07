# First Stage: Build the application using Maven and JDK 17
FROM maven:3.8.1-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests
FROM openjdk:17
COPY --from=build /target/*.jar express.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "express.jar"]
