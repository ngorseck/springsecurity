FROM openjdk:8
COPY target/evalsecurity.jar evalsecurity.jar
EXPOSE 8087
ENTRYPOINT ["java", "-jar", "evalsecurity.jar"]