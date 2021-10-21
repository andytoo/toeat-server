FROM arm64v8/openjdk:11-jre
ENV APP_HOME=/app
WORKDIR $APP_HOME
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]