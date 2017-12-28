FROM openjdk:8-jdk-alpine
EXPOSE 8765
ADD /target/gateway.jar gateway.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gateway.jar"]