# temp container to build using gradle
FROM gradle:5.3.0-jdk-alpine AS TOEAT_GRADLE_IMAGE
ENV APP_HOME=/app
WORKDIR $APP_HOME
COPY build.gradle settings.gradle $APP_HOME

COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src

RUN gradle build || return 0
COPY . .
RUN gradle clean build

# actual container
FROM adoptopenjdk/openjdk11:alpine-jre
ENV ARTIFACT_NAME=api-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/app

WORKDIR $APP_HOME
COPY --from=TOEAT_GRADLE_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]