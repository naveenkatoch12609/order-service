# 1. Use a base image with Java 17
FROM eclipse-temurin:17-jdk

# 2. Set working directory inside the container
WORKDIR /app

# 3. Copy your built JAR file into the container
COPY target/order-service-0.0.1-SNAPSHOT.jar app.jar

# 4. Expose the port your app uses
EXPOSE 8080

# 5. Command to run your app
ENTRYPOINT ["java", "-jar", "app.jar"]