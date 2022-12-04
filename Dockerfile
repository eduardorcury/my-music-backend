FROM amazoncorretto:11
WORKDIR /app
COPY . .
RUN sh gradlew build
EXPOSE 8080
ENTRYPOINT java -DCLIENT_ID=$CLIENT_ID -DCLIENT_SECRET=$CLIENT_SECRET -jar build/libs/my-music-0.1-all.jar