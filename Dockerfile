FROM openjdk:17-jdk-alpine
EXPOSE 8088
VOLUME /backend_volume
ARG JAR_FILE=target/*.jar
COPY ./target/demo-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]