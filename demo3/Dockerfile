# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy only pom.xml first (for dependency caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Package the application (skip tests for speed)
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your app runs on (default 8080)
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
