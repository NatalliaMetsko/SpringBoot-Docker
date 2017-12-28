FROM openjdk:8-jdk-alpine
EXPOSE 8761
ADD /target/discovery.jar discovery.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/discovery.jar"]