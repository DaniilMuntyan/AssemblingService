FROM openjdk:11-jdk
EXPOSE 8080
ADD target/rest-app-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","./app.jar"]