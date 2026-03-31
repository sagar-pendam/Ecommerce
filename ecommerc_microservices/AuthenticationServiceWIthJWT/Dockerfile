# ---------- BUILD STAGE ----------
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

# Copy entire project (IMPORTANT)
COPY . .

# Build only Auth module from ROOT
RUN mvn clean package -pl AuthenticationServiceWIthJWT -am -DskipTests


# ---------- RUN STAGE ----------
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /app/AuthenticationServiceWIthJWT/target/*.jar app.jar

EXPOSE 8087

ENTRYPOINT ["java", "-jar", "app.jar"]