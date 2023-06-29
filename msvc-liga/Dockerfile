FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY ./target/msvc-liga.jar .

EXPOSE 8002

#CMD ["java", "-Dspring.profiles.active=prod", "-jar", "msvc-login.jar"]
CMD ["java", "-jar", "msvc-liga.jar"]