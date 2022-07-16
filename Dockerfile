FROM amazoncorretto:11
WORKDIR /app
COPY . .
RUN sh gradlew build
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "build/libs/my-music-0.1-all.jar"]