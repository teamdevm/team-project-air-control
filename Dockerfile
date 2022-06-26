
FROM openjdk:18-slim AS base

COPY .mvn/ .mvn
COPY mvnw ./
COPY pom.xml ./
RUN sed -i 's/\r$//' mvnw
RUN chmod +x mvnw
RUN ./mvnw -B dependency:go-offline -DskipTests
COPY src ./src

FROM base as build
<<<<<<< Updated upstream

=======
COPY src/ ./src
COPY front/index.html ./src/main/resources/templates
COPY front/css/ ./src/main/resources/static/css
COPY front/emojis/ ./src/main/resources/static/emojis
COPY front/fonts/ ./src/main/resources/static/fonts
COPY front/scripts/ ./src/main/resources/static/scripts
>>>>>>> Stashed changes
RUN ./mvnw -B clean package -DskipTests

#comment
FROM openjdk:18-slim
COPY --from=build target/air-*.jar /air.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/air.jar"]