FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .

WORKDIR /app/review-service
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/review-service/target/*.jar app.jar

EXPOSE 9095
ENTRYPOINT ["java", "-jar", "app.jar"]