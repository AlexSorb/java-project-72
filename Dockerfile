FROM gradle:8.5.0-jdk21

WORKDIR /app

COPY /app .

RUN export JDBC_DATABASE_URL=jdbc:postgresql://db:5432/postgres?password=password&user=postgres

RUN gradle installDist --info

CMD ./build/install/app/bin/app