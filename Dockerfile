FROM openjdk:8-jre-alpine
VOLUME /tmp
COPY build/libs/withdraw-service-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT java $JAVA_OPTIONS -jar /app.jar
