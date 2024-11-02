FROM openjdk:15-jdk-alpine as builder
COPY ./ /app
WORKDIR /app
RUN ./gradlew build --stacktrace

FROM openjdk:15-jdk-alpine as runtime
COPY --from=builder /app/build /build
RUN mkdir /app
RUN cp /build/libs/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]