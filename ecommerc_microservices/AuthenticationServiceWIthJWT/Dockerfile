FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# Copy parent pom.xml first
COPY pom.xml .

# Copy the specific service
COPY AuthenticationServiceWIthJWT/pom.xml AuthenticationServiceWIthJWT/pom.xml
COPY AuthenticationServiceWIthJWT/src AuthenticationServiceWIthJWT/src

# Build only the specific service module
RUN mvn clean package -pl AuthenticationServiceWIthJWT -am -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/AuthenticationServiceWIthJWT/target/*.jar app.jar
EXPOSE 8087
ENTRYPOINT ["java", "-jar", "app.jar"]
