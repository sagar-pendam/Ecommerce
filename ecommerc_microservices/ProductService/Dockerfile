FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .

WORKDIR /app/ProductService
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/ProductService/target/*.jar app.jar

EXPOSE 8086
ENTRYPOINT ["java", "-jar", "app.jar"]