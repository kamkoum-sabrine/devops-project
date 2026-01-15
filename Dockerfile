FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app
RUN useradd -m appuser
USER appuser

COPY target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
