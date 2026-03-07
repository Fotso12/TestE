package com.ecomove.repositories;

import com.ecomove.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface de dépôt pour l'entité Utilisateur.
 *
 * @author Darryl
 * @version 1.0
 */
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    /**
     * Recherche un utilisateur par son adresse email.
     * @param email L'adresse email à rechercher.
     * @return Un Optional contenant l'utilisateur s'il existe.
     */
    Optional<Utilisateur> findByEmail(String email);
}
