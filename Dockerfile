FROM openjdk:latest
RUN cd /opt/app && chmod +x mvn && ./mvn clean package
ARG JAR_FILE=/opt/app/target/DOTA-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY  ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
