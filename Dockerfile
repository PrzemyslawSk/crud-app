# Build stage
FROM maven:3.9.8-amazoncorretto-17 AS build
WORKDIR /home/app
COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package

# Package stage
FROM amazoncorretto:17
COPY --from=build /home/app/target/*.jar crud-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/crud-app.jar" ]