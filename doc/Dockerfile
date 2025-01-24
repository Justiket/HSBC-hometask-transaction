FROM openjdk:21-jdk-slim

WORKDIR /app

COPY ../target/HSBC-hometask-transaction-0.0.1-SNAPSHOT.jar /app/HSBC-hometask-transaction-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/HSBC-hometask-transaction-0.0.1-SNAPSHOT.jar"]