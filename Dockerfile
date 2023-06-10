FROM eclipse-temurin:8-jre-alpine

ARG APP_NAME="taco-cloud-0.0.1"
ARG JAR_FILE="/target/${APP_NAME}.jar"

COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java","-jar", "application.jar"]