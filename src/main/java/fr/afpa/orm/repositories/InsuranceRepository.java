package fr.afpa.orm.repositories;

import fr.afpa.orm.entities.Client;
import fr.afpa.orm.entities.Insurance;
import org.springframework.data.repository.CrudRepository;

public interface InsuranceRepository extends CrudRepository<Insurance, Integer> {
}
