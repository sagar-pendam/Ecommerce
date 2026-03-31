FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# Copy parent pom.xml first
COPY pom.xml .

# Copy the specific service
COPY wishlist-service/pom.xml wishlist-service/pom.xml
COPY wishlist-service/src wishlist-service/src

# Build only the specific service module
RUN mvn clean package -pl wishlist-service -am -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/wishlist-service/target/*.jar app.jar
EXPOSE 8091
ENTRYPOINT ["java", "-jar", "app.jar"]
