FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# Copy parent pom.xml first
COPY pom.xml .

# Copy the specific service
COPY cart-service/pom.xml cart-service/pom.xml
COPY cart-service/src cart-service/src

# Build only the specific service module
RUN mvn clean package -pl cart-service -am -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/cart-service/target/*.jar app.jar
EXPOSE 9094
ENTRYPOINT ["java", "-jar", "app.jar"]
