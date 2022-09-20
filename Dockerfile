FROM openjdk:17-alpine
EXPOSE 8080
COPY diansLab-0.0.1-SNAPSHOT.jar dockerdemo.jar
ENTRYPOINT ["java","-jar","dockerdemo.jar"]