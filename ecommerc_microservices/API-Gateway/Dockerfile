FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# Copy parent pom.xml first
COPY pom.xml .

# Copy the specific service
COPY API-Gateway/pom.xml API-Gateway/pom.xml
COPY API-Gateway/src API-Gateway/src

# Build only the specific service module
RUN mvn clean package -pl API-Gateway -am -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/API-Gateway/target/*.jar app.jar
EXPOSE 9191
ENTRYPOINT ["java", "-jar", "app.jar"]
