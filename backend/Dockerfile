# Use official OpenJDK image as base
FROM eclipse-temurin:17-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy all project files into container
COPY . .

# Build the application
RUN ./mvnw clean package -DskipTests

# Run the jar file
ENTRYPOINT ["java", "-jar", "target/*.jar"]