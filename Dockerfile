FROM openjdk:11

RUN mkdir /usr/src/restapp

WORKDIR /usr/src/restapp/

EXPOSE 8787
EXPOSE 8080

ARG JAR_FILE

COPY ${JAR_FILE} /usr/src/restapp/app.jar

CMD ["java", "-jar", "-agentlib:jdwp=transport=dt_socket,address=8787,server=y,suspend=n", "app.jar"]