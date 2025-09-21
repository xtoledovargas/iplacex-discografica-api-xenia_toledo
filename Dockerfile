 #= Stage 1: build

FROM gradle:8.7-jdk21 AS builder

WORKDIR /app

COPY build.gradle .
COPY settings.gradle .
COPY src ./src

RUN gradle build --no-daemon

# = Stage 2:
    
FROM openjdk:21-jdk-slim

WORKDIR /app


COPY --from=builder /app/build/libs/*.jar /discografia/app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
