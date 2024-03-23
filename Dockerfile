FROM openjdk:17-alpine
WORKDIR /app
COPY ./target/task-management-1.0.0.jar .
EXPOSE 8089
ENTRYPOINT ["java","-jar","task-management-1.0.0.jar"]