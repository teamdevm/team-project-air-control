
FROM openjdk:18-slim AS base

COPY .mvn/ .mvn
COPY mvnw ./
COPY pom.xml ./
RUN sed -i 's/\r$//' mvnw
RUN chmod +x mvnw
RUN ./mvnw -B dependency:go-offline -DskipTests
COPY src ./src

FROM base as build

RUN ./mvnw -B clean package -DskipTests

#comment
FROM openjdk:18-slim
COPY --from=build target/air-*.jar /air.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/air.jar"]