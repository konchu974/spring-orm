package fr.afpa.orm.repositories;

import fr.afpa.orm.entities.Insurance;

import org.springframework.data.repository.CrudRepository;

/**
 * TODO : proposition d'évolution
 * Il peut être possible de changer "CrudRepository" en JpaRepository
 * 
 * Avantage que cela pourrait procurer : les fonctions susceptibles de revnoyer des collections (telles que "findAll")
 * revoie des "Iterable" pour CrudRepository et des "List<T>" pour JpaRepository
 * 
 * Dans le cas d'entité il est proposé de les transformer en DTO via l'utisation d'un mapper.
 * Le mapper s'utilise très facilement avec la méthode "map" qui s'applique à un stream (exemple : https://www.baeldung.com/java-iterable-to-stream#performing-stream-operation)
 * par contre le stream ne s'obtient pas de façon directe d'un iterable... Dommage.
 * 
 * Le fait de recupérer une "List<T>" simplifie le code du mapping dans le contrôleur (cf. commentaire dans la classe "InsuranceRestController")
 */
public interface InsuranceRepository extends CrudRepository<Insurance, Integer> {
}
