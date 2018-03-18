FROM alpine/git as clone 
WORKDIR /app
RUN git clone https://github.com/Casanova2/planet_of_the_tapes.git -b develop

FROM maven:3.5-jdk-8-alpine as build 
WORKDIR /app
COPY --from=clone /app/planet_of_the_tapes /app 
RUN mvn package

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build /app/target/planet_of_the_tapes-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8443
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar app.jar"]