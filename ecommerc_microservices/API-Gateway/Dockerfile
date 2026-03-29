FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/API-Gateway-1.0.0.jar app.jar
EXPOSE 9191
ENTRYPOINT ["java","-jar","app.jar"]
