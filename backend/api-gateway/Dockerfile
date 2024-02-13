FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/api-gateway

COPY ./api-gateway/pom.xml /app
COPY ./api-gateway/.mvn ./.mvn
COPY ./api-gateway/mvnw .
COPY ./api-gateway/pom.xml .

RUN ./mvnw dependency:go-offline
COPY ./api-gateway/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/api-gateway/target/api-gateway-0.0.1-SNAPSHOT.jar .
EXPOSE 8080

CMD  sleep 15 && java -jar api-gateway-0.0.1-SNAPSHOT.jar