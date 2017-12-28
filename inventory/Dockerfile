FROM openjdk:8-jdk-alpine
EXPOSE 8082
ADD /target/inventory.jar inventory.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container","-jar","/inventory.jar"]