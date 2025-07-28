package fr.afpa.orm.repositories;

import fr.afpa.orm.entities.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * TODO La classe CrudRepository<T, K> est une classe générique paramétrée avec 2 classe
 * T : la nature des objets manipulées par l'ORM
 * K : le type de la clef primaire des objets en questions.
 * 
 * Sachant que Client a sa clef primaire en UUID ceci il faudrait que K soit UUID
 */
@Repository
public interface ClientRepository extends CrudRepository<Client, UUID> {
}
