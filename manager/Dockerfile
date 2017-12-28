FROM openjdk:8-jdk-alpine
EXPOSE 8083
ADD /target/manager.jar manager.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/manager.jar"]