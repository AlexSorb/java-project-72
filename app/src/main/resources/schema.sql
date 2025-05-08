DROP TABLE IF EXISTS urls;
DROP TABLE IF EXISTS url_checks;

CREATE TABLE urls (
    id serial PRIMARY KEY,
    name varchar(255) NOT NULL,
    created_at timestamp
);

CREATE TABLE url_checks (
    id serial PRIMARY KEY,
    url_id integer REFERENCES urls(id),
    status_code text,
    h1 text,
    title text,
    description text,
    created_at timestamp
);
