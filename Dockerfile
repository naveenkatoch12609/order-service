# -------- Stage 1: Build the app --------
FROM eclipse-temurin:17-jdk as builder

WORKDIR /app

# Copy project files
COPY . .

# Build the Spring Boot JAR
RUN ./mvnw clean package -DskipTests

# -------- Stage 2: Run the app --------
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy the built JAR from stage 1
COPY --from=builder /app/target/order-service-0.0.1-SNAPSHOT.jar order-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "order-service.jar"]
