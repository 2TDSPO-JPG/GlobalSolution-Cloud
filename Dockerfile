FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre

RUN useradd -m astro

WORKDIR /home/astro/app

COPY --from=build /app/target/*.jar app.jar

ENV USER_DB_ORACLE=system
ENV PASSWORD_DB_ORACLE=Oracle123

USER astro

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
