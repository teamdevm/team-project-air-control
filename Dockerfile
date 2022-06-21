
FROM openjdk:18-slim AS build

COPY . .
#COPY .mvn/ .mvn
#COPY mvnw ./
#COPY pom.xml ./
RUN sed -i 's/\r$//' mvnw
RUN chmod +x mvnw
RUN ./mvnw -B dependency:go-offline -DskipTests

RUN ./mvnw -B clean package -DskipTests


FROM openjdk:18-slim
COPY --from=build target/air-0.0.1.jar /air.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/air.jar"]