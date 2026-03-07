package com.ecomove.services;

import com.ecomove.dtos.ReservationDTO;
import java.util.List;

/**
 * Interface définissant les opérations de service pour les réservations.
 *
 * @author Darryl
 * @version 1.0
 */
public interface ReservationService {
    /**
     * Effectue une nouvelle réservation.
     * @param reservationDTO Les données de réservation.
     * @return La réservation créée.
     */
    ReservationDTO reserverTrajet(ReservationDTO reservationDTO);

    /**
     * Récupère une réservation par son ID.
     * @param id L'ID de la réservation.
     * @return La réservation trouvée.
     */
    ReservationDTO obtenirReservationParId(Long id);

    /**
     * Liste toutes les réservations.
     * @return Liste des réservations.
     */
    List<ReservationDTO> obtenirToutesLesReservations();

    /**
     * Met à jour le statut d'une réservation.
     * @param id L'ID de la réservation.
     * @param reservationDTO Les nouvelles données.
     * @return La réservation mise à jour.
     */
    ReservationDTO mettreAJourReservation(Long id, ReservationDTO reservationDTO);

    /**
     * Annule une réservation.
     * @param id L'ID de la réservation à supprimer.
     */
    void annulerReservation(Long id);
}
