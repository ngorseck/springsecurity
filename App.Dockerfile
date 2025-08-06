FROM openjdk:8
ADD target/evalsecurrity-0.0.1-SNAPSHOT.jar evalsecurrity.jar
EXPOSE 8087
ENTRYPOINT ["java", "-jar", "evalsecurrity.jar"]