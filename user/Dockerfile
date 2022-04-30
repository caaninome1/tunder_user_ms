FROM eclipse-temurin:17-jre-focal
COPY "./target/user-0.0.1-SNAPSHOT.war" "app.war"
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.war"]