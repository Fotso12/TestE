package com.ecomove.entities;

import com.ecomove.utils.RoleUtilisateur;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Entité représentant un utilisateur du système EcoMove.
 * Un utilisateur peut être un conducteur, un passager ou un administrateur.
 *
 * @author Darryl
 * @version 1.0
 */
@Entity
@Table(name = "utilisateurs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String motDePasse;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleUtilisateur role;

    @OneToMany(mappedBy = "conducteur", cascade = CascadeType.ALL)
    private List<Trajet> trajetsProposes;

    @OneToMany(mappedBy = "passager", cascade = CascadeType.ALL)
    private List<Reservation> reservations;
}
