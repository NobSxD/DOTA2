FROM openjdk:latest
COPY . /opt/app
RUN cd /opt/app && chmod +x mvnw && ./mvnw clean package
FROM openjdk:latest
WORKDIR /opt/app
COPY --from=build /opt/app/target/${JAR_NAME}.jar /opt/app/${JAR_NAME}.jar
ENTRYPOINT exec java -jar /opt/app/{JAR_NAME}.jar
