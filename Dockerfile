FROM gradle:8.5.0-jdk21

WORKDIR /app

COPY .

RUN ./gradlew --no-daemon dependencies


ENV JAVA_OPTS="-Xmx512M -Xms512M"
EXPOSE 7070

CMD ["java", "-jar", "build/libs/app-1.0-SNAPSHOT-all.jar"]