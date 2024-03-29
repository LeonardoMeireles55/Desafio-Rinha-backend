#BUILD
FROM openjdk:21-slim AS build
WORKDIR /usr/src/app
COPY . .
RUN ./mvnw clean package -DskipTests

#RUN
FROM openjdk:21-slim
WORKDIR /usr/src/app
COPY --from=build /usr/src/app/target/rinha-backend-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar","app.jar"]