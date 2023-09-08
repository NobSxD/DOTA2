FROM openjdk:latest
RUN  ./mvnw clean package
ARG JAR_FILE=/target/DOTA-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY  ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
