FROM openjdk:21

WORKDIR /app

COPY target/HSBC-hometask-transaction-0.0.1-SNAPSHOT.jar /app/HSBC-hometask-transaction-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/app/HSBC-hometask-transaction-0.0.1-SNAPSHOT.jar"]