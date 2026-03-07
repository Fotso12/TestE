package com.ecomove.repositories;

import com.ecomove.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface de dépôt pour l'entité Reservation.
 *
 * @author Darryl
 * @version 1.0
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    /**
     * Recherche les réservations effectuées par un passager spécifique.
     * @param passagerId L'ID du passager.
     * @return La liste des réservations du passager.
     */
    List<Reservation> findByPassagerId(Long passagerId);
}
