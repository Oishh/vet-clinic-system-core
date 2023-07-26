FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar vet-clinic-system-core-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/vet-clinic-system-core-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080