CREATE TABLE users(
    id bigserial PRIMARY KEY,
    name varchar(255) NOT NULL,
    age int NOT NULL,
    email varchar(255) NOT NULL,
    created timestamp with time zone NOT NULL DEFAULT now()
);

