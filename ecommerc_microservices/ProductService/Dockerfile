# ---------- BUILD STAGE ----------
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

# Copy entire project (IMPORTANT for multi-module)
COPY . .

# Build only ProductService module
RUN mvn clean package -pl ProductService -am -DskipTests


# ---------- RUN STAGE ----------
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /app/ProductService/target/*.jar app.jar

EXPOSE 8086

ENTRYPOINT ["java", "-jar", "app.jar"]