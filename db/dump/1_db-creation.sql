
DROP TABLE if exists public.account CASCADE;

/* TODO : ajouter la création de la table "client" */

CREATE TABLE client (
    id uuid,
    first_name VARCHAR(100),
    last_name VARCHAR(50),
    email VARCHAR(50),
    birthdate date,
    PRIMARY KEY (id)
);


CREATE TABLE account (
	id SERIAL PRIMARY KEY,
	creationTime timestamp,
	balance BIGINT,
    client_id uuid,
    active BOOLEAN,
    FOREIGN KEY(client_id) REFERENCES client(id)
);

