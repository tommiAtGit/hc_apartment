FROM adoptopenjdk/openjdk14:alpine-jre
WORKDIR /opt/app
ARG JAR_FILE=target/housingApartment-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 9091
ENTRYPOINT ["java","-jar","/opt/app/app.jar"]

HEALTHCHECK CMD curl --fail http://localhost:9091/api/hc_apartment/health || exit 1

CMD java -jar /opt/app/app.jar