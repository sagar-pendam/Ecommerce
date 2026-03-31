FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# Copy parent pom.xml first
COPY pom.xml .

# Copy the specific service
COPY SpringBootMs-ConfigServer/pom.xml SpringBootMs-ConfigServer/pom.xml
COPY SpringBootMs-ConfigServer/src SpringBootMs-ConfigServer/src

# Build only the specific service module
RUN mvn clean package -pl SpringBootMs-ConfigServer -am -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/SpringBootMs-ConfigServer/target/*.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "app.jar"]
