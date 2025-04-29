FROM gradle:8.5.0-jdk21

WORKDIR /app

COPY /app .

RUN gradle installDist --info

CMD ./build/install/app/bin/app