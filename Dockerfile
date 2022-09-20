FROM openjdk:17-alpine

EXPOSE 8080

COPY target/docker-demo.jar dockerdemo.jar

ENTRYPOINT ["java","-jar","dockerdemo.jar"]