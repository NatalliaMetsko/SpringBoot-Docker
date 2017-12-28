FROM openjdk:8-jdk-alpine
EXPOSE 8081
ADD /target/catalog.jar catalog.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=container","-jar","/catalog.jar"]