DROP TABLE IF EXISTS urls;

CREATE TABLE urls (
    id integer PRIMARY KEY AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    created_at timestamp
);