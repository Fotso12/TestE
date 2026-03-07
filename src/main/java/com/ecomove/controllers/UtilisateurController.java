package com.ecomove.controllers;

import com.ecomove.dtos.UtilisateurDTO;
import com.ecomove.services.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour la gestion des utilisateurs.
 *
 * @author Darryl
 * @version 1.0
 */
@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
@Tag(name = "Utilisateur", description = "API de gestion des utilisateurs d'EcoMove")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @PostMapping
    @Operation(summary = "Créer un nouvel utilisateur", description = "Enregistre un nouvel utilisateur dans le système")
    public ResponseEntity<UtilisateurDTO> creerUtilisateur(@RequestBody UtilisateurDTO dto) {
        return ResponseEntity.ok(utilisateurService.creerUtilisateur(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un utilisateur par ID")
    public ResponseEntity<UtilisateurDTO> obtenirUtilisateurParId(@PathVariable Long id) {
        return ResponseEntity.ok(utilisateurService.obtenirUtilisateurParId(id));
    }

    @GetMapping
    @Operation(summary = "Lister tous les utilisateurs")
    public ResponseEntity<List<UtilisateurDTO>> obtenirTousLesUtilisateurs() {
        return ResponseEntity.ok(utilisateurService.obtenirTousLesUtilisateurs());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un utilisateur")
    public ResponseEntity<UtilisateurDTO> mettreAJourUtilisateur(@PathVariable Long id, @RequestBody UtilisateurDTO dto) {
        return ResponseEntity.ok(utilisateurService.mettreAJourUtilisateur(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un utilisateur")
    public ResponseEntity<Void> supprimerUtilisateur(@PathVariable Long id) {
        utilisateurService.supprimerUtilisateur(id);
        return ResponseEntity.noContent().build();
    }
}
