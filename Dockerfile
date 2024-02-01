FROM openjdk:17-oracle
RUN mkdir -p /opt/app
WORKDIR /opt/app
COPY target/nuevo-spa-app-v1-0.0.1-SNAPSHOT.jar /opt/app/target/
EXPOSE 8080
CMD ["sh", "-c", "java -jar /opt/app/target/nuevo-spa-app-v1-0.0.1-SNAPSHOT.jar"]