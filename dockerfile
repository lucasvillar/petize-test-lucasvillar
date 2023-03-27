FROM gradle:7.6.0-jdk17 as build
COPY . /home/gradle/app
COPY --chown=gradle:gradle . /home/gradle/app
WORKDIR /home/gradle/app
RUN ./gradlew clean build --no-daemon
RUN rm /home/gradle/app/build/libs/*plain*


FROM eclipse-temurin:17.0.6_10-jdk-jammy
VOLUME /tmp
ARG BUILD_PATH=/home/gradle/app/build/libs

COPY --from=build ${BUILD_PATH}/* /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
