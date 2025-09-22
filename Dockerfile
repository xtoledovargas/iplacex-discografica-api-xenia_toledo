# syntax=docker/dockerfile:1

########### Stage 1: Build con Gradle ############
FROM gradle:8.7.0-jdk17 AS build
WORKDIR /workspace

# Copiamos archivos para cachear dependencias
COPY build.gradle settings.gradle ./
COPY gradlew ./
COPY gradle ./gradle
RUN chmod +x gradlew

# Copiamos el código fuente
COPY src ./src

# Construimos el JAR
RUN ./gradlew --no-daemon clean bootJar

########### Stage 2: Runtime con OpenJDK #########
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiamos el jar (version = 1 → *-1.jar)
COPY --from=build /workspace/build/libs/*-1.jar /app/app.jar

# (Render usa PORT por env; EXPOSE es informativo)
EXPOSE 8080
ENV JAVA_OPTS=""

CMD ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]

