FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# Copy parent pom.xml first
COPY pom.xml .

# Copy the specific service
COPY ProductService/pom.xml ProductService/pom.xml
COPY ProductService/src ProductService/src

# Build only the specific service module
RUN mvn clean package -pl ProductService -am -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/ProductService/target/*.jar app.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "app.jar"]
