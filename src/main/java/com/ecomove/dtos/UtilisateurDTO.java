package com.ecomove.dtos;

import com.ecomove.utils.RoleUtilisateur;
import lombok.*;

/**
 * Data Transfer Object pour l'utilisateur.
 *
 * @author Darryl
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtilisateurDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private RoleUtilisateur role;
}
