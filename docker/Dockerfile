FROM eclipse-temurin:17-jre-alpine
#COPY ../build/libs/utility-meter-1.0-SNAPSHOT.jar .
ADD ./utility-meter-1.0-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]
