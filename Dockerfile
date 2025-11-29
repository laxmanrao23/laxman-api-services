FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY . .

# Build the app (Maven)
RUN ./mvnw -q -DskipTests package

EXPOSE 8080

# Use stable jar name from pom.xml <finalName>app</finalName>
CMD ["java", "-jar", "target/app.jar"]
