FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY ./target/msvc-liga.jar .

EXPOSE 8000

CMD ["java", "-Dspring.profiles.active=prod", "-jar", "msvc-liga.jar"]