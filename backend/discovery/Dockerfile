FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/discovery

COPY ./discovery/pom.xml /app
COPY ./discovery/.mvn ./.mvn
COPY ./discovery/mvnw .
COPY ./discovery/pom.xml .

RUN ./mvnw dependency:go-offline
COPY ./discovery/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/discovery/target/discovery-0.0.1-SNAPSHOT.jar .
EXPOSE 8761

CMD ["java", "-jar", "discovery-0.0.1-SNAPSHOT.jar"]