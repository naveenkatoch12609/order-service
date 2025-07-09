# Use a lightweight OpenJDK base image
FROM eclipse-temurin:17-jdk as builder

# Set working directory
WORKDIR /app

# Copy your Maven/Gradle wrapper files and project files
COPY . .

# Build the Spring Boot JAR using Maven (skip tests for speed)
RUN ./mvnw clean package -DskipTests

# ---- Create final image ----
FROM eclipse-temurin:17-jdk

# Create app directory
WORKDIR /app

# Copy the fat JAR from the builder image
COPY --from=builder /app/target/order-service-*.jar order-service.jar

# Expose the config server port
EXPOSE 8888

# Set entrypoint to run the Spring Boot app
ENTRYPOINT ["java", "-jar", "order-service.jar"]
