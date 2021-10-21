FROM arm64v8/openjdk:11-jre
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
RUN ./gradlew build
COPY build/libs/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]