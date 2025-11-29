FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY . .

RUN ./mvnw -q -DskipTests package

EXPOSE 8080

CMD ["java", "-jar", "target/laxman-api-services-0.0.1-SNAPSHOT.jar"]
