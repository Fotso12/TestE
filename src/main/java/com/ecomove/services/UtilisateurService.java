package com.ecomove.services;

import com.ecomove.dtos.UtilisateurDTO;
import java.util.List;

/**
 * Interface définissant les opérations de service pour les utilisateurs.
 *
 * @author Darryl
 * @version 1.0
 */
public interface UtilisateurService {
    /**
     * Crée un nouvel utilisateur.
     * @param utilisateurDTO Les données de l'utilisateur à créer.
     * @return L'utilisateur créé.
     */
    UtilisateurDTO creerUtilisateur(UtilisateurDTO utilisateurDTO);

    /**
     * Récupère un utilisateur par son identifiant.
     * @param id L'identifiant de l'utilisateur.
     * @return L'utilisateur trouvé.
     */
    UtilisateurDTO obtenirUtilisateurParId(Long id);

    /**
     * Récupère tous les utilisateurs.
     * @return La liste de tous les utilisateurs.
     */
    List<UtilisateurDTO> obtenirTousLesUtilisateurs();

    /**
     * Met à jour un utilisateur existant.
     * @param id L'identifiant de l'utilisateur à modifier.
     * @param utilisateurDTO Les nouvelles données.
     * @return L'utilisateur mis à jour.
     */
    UtilisateurDTO mettreAJourUtilisateur(Long id, UtilisateurDTO utilisateurDTO);

    /**
     * Supprime un utilisateur.
     * @param id L'identifiant de l'utilisateur à supprimer.
     */
    void supprimerUtilisateur(Long id);
}
