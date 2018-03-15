FROM java:8
COPY ../target/src src/
CMD ["ls"]
EXPOSE 8443
ADD ../target/planet_of_the_tapes-0.0.1-SNAPSHOT.jar planet_of_the_tapes-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","planet_of_the_tapes-0.0.1-SNAPSHOT.jar"]
