FROM openjdk:latest
COPY . /srv
RUN cd /srv && chmod +x mvnw && ./mvnw clean package
FROM openjdk:latest
WORKDIR /srv
COPY --from=build /srv/target/${JAR_NAME}.jar /srv/${JAR_NAME}.jar
ENTRYPOINT exec java -jar /srv/{JAR_NAME}.jar
