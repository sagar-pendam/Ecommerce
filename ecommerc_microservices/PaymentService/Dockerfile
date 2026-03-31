FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# Copy parent pom.xml first
COPY pom.xml .

# Copy the specific service
COPY PaymentService/pom.xml PaymentService/pom.xml
COPY PaymentService/src PaymentService/src

# Build only the specific service module
RUN mvn clean package -pl PaymentService -am -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/PaymentService/target/*.jar app.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "app.jar"]
