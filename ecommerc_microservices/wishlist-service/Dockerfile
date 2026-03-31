FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .

WORKDIR /app/wishlist-service
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/wishlist-service/target/*.jar app.jar

EXPOSE 8091
ENTRYPOINT ["java", "-jar", "app.jar"]