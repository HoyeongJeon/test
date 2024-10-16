FROM gradle:8.10.1-jdk17 AS build

WORKDIR /app

COPY . /app

RUN gradle clean build --no-daemon

FROM openjdk:17.0.1-jdk-slim

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/backend.jar

EXPOSE 8080
ENTRYPOINT ["java"]
CMD ["-jar", "backend.jar"]