FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /app
COPY . .
RUN mvn install

FROM eclipse-temurin:17-jre-focal
WORKDIR /app
EXPOSE 8080
COPY --from=builder "/app/target/user-0.0.1-SNAPSHOT.war" "app.war"
ENTRYPOINT ["java", "-jar", "app.war"]
