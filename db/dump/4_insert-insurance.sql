INSERT INTO insurance (name) VALUES
                                 ('Assurance Vie'),
                                 ('Mutuelle Santé'),
                                 ('Assurance Auto'),
                                 ('Assurance Habitation'),
                                 ('Assurance Voyage');

CREATE OR REPLACE PROCEDURE create_souscriptions()
LANGUAGE plpgsql
AS $$
DECLARE
client_uuid UUID;
    insurance_id INTEGER;
    nb_clients INTEGER;
    nb_insurances INTEGER;
BEGIN
    -- Nombre total de clients
SELECT COUNT(*) INTO nb_clients FROM public."client";
-- Nombre total d'assurances
SELECT COUNT(*) INTO nb_insurances FROM public.insurance;

FOR i IN 1..200 LOOP
        -- Choisir un client UUID au hasard
SELECT id INTO client_uuid
FROM public."client"
         OFFSET floor(random() * nb_clients)::int LIMIT 1;

-- Choisir une assurance au hasard
insurance_id := floor(random() * nb_insurances + 1)::int;

BEGIN
INSERT INTO public.souscri (id_client, id_insurance)
VALUES (client_uuid, insurance_id);
EXCEPTION WHEN unique_violation THEN
            -- Ignore si déjà existant (pas de doublons)
END;
END LOOP;
END;
$$;

-- Appeler la procédure
CALL create_souscriptions();

