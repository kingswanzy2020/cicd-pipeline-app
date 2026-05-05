# Use a minimal Java 21 runtime image
FROM eclipse-temurin:21-jre-alpine
# Set the working directory inside the container
WORKDIR /app
# Copy the compiled JAR from Maven's output
COPY target/cicd-pipeline-app-1.0-SNAPSHOT.jar app.jar
# Document the port the app listens on
EXPOSE 8080
# Run the JAR when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]