FROM gradle:8.5.0-jdk21

WORKDIR /app

COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY gradlew .
COPY Makefile .

COPY src src
COPY config config

RUN make dev

CMD ./build/install/app/bin/app