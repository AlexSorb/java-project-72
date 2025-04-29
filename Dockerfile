FROM gradle:8.5.0-jdk21

WORKDIR /app

COPY /app .

RUN gradle installShadowDist

CMD ./build/install/app-shadow/bin/app