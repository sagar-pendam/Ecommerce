FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -pl API-Gateway -am -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/API-Gateway/target/*.jar app.jar
EXPOSE 9191
ENTRYPOINT ["java", "-jar", "app.jar"]