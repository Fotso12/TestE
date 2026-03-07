package com.ecomove.services;

import com.ecomove.dtos.TrajetDTO;
import java.util.List;

/**
 * Interface définissant les opérations de service pour les trajets.
 *
 * @author Darryl
 * @version 1.0
 */
public interface TrajetService {
    /**
     * Propose un nouveau trajet.
     * @param trajetDTO Les données du trajet.
     * @return Le trajet créé.
     */
    TrajetDTO creerTrajet(TrajetDTO trajetDTO);

    /**
     * Récupère un trajet par son ID.
     * @param id L'ID du trajet.
     * @return Le trajet trouvé.
     */
    TrajetDTO obtenirTrajetParId(Long id);

    /**
     * Récupère tous les trajets disponibles.
     * @return Liste des trajets.
     */
    List<TrajetDTO> obtenirTousLesTrajets();

    /**
     * Met à jour les informations d'un trajet.
     * @param id L'ID du trajet.
     * @param trajetDTO Les nouvelles informations.
     * @return Le trajet mis à jour.
     */
    TrajetDTO mettreAJourTrajet(Long id, TrajetDTO trajetDTO);

    /**
     * Annule un trajet.
     * @param id L'ID du trajet à supprimer.
     */
    void supprimerTrajet(Long id);
}
