#
# Set a variable that can be used in all stages.
#
ARG BUILD_HOME=/gradle-build

#
# Gradle image for the build stage.
#
FROM gradle:7.2.0-jdk11 AS build-image

#
# Set the working directory.
#
ARG BUILD_HOME
ENV APP_HOME=$BUILD_HOME
WORKDIR $APP_HOME

#
# Copy the Gradle config, source code, and static analysis config
# into the build container.
#
COPY --chown=gradle:gradle . $APP_HOME

#
# Build the application.
#
RUN gradle --no-daemon build

#
# Java image for the application to run in.
#
FROM adoptopenjdk/openjdk11:alpine-jre

#
# Copy the jar file in and name it app.jar.
#
ARG BUILD_HOME
ENV ARTIFACT_NAME=api-0.0.1-SNAPSHOT.jar
ENV APP_HOME=$BUILD_HOME
COPY --from=build-image $APP_HOME/build/libs/$ARTIFACT_NAME app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]