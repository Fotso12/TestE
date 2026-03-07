package com.ecomove.repositories;

import com.ecomove.entities.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface de dépôt pour l'entité Trajet.
 *
 * @author Darryl
 * @version 1.0
 */
@Repository
public interface TrajetRepository extends JpaRepository<Trajet, Long> {
    /**
     * Recherche les trajets par point de départ.
     * @param pointDepart Le point de départ.
     * @return La liste des trajets correspondants.
     */
    List<Trajet> findByPointDepart(String pointDepart);
}
