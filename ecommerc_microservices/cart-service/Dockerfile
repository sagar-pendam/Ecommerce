FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .

WORKDIR /app/cart-service
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/cart-service/target/*.jar app.jar

EXPOSE 9094
ENTRYPOINT ["java", "-jar", "app.jar"]