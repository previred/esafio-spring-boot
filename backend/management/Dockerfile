FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/management

COPY ./management/target/management-0.0.1-SNAPSHOT.jar /app/management/target/management-0.0.1-SNAPSHOT.jar


FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/management/target/management-0.0.1-SNAPSHOT.jar .
EXPOSE 8090

CMD  sleep 30 && java -jar management-0.0.1-SNAPSHOT.jar