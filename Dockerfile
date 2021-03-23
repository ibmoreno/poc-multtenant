# multitenant:1.0.0
FROM adoptopenjdk/openjdk11:alpine
EXPOSE 8080
COPY target/*.jar app.jar


# seta variavel de ambiente do timezone
ENV TZ="America/Cuiaba"
RUN apk add tzdata

ENTRYPOINT ["sh", "-c", "java -jar app.jar"]