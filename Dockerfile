# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file
COPY target/springboot-scaffold-1.0.0.jar app.jar

# Expose the port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/api/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# Optional: Set JVM options
ENV JAVA_OPTS="-Xmx512m -Xms256m"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]