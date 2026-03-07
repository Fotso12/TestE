package com.ecomove.controllers;

import com.ecomove.dtos.TrajetDTO;
import com.ecomove.services.TrajetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour la gestion des trajets.
 *
 * @author Darryl
 * @version 1.0
 */
@RestController
@RequestMapping("/api/trajets")
@RequiredArgsConstructor
@Tag(name = "Trajet", description = "API de gestion des trajets de covoiturage")
public class TrajetController {

    private final TrajetService trajetService;

    @PostMapping
    @Operation(summary = "Proposer un trajet", description = "Permet à un conducteur de publier un nouveau trajet")
    public ResponseEntity<TrajetDTO> creerTrajet(@RequestBody TrajetDTO dto) {
        return ResponseEntity.ok(trajetService.creerTrajet(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir les détails d'un trajet")
    public ResponseEntity<TrajetDTO> obtenirTrajetParId(@PathVariable Long id) {
        return ResponseEntity.ok(trajetService.obtenirTrajetParId(id));
    }

    @GetMapping
    @Operation(summary = "Lister tous les trajets disponibles", description = "Récupère la liste de tous les trajets publiés")
    public ResponseEntity<List<TrajetDTO>> obtenirTousLesTrajets() {
        return ResponseEntity.ok(trajetService.obtenirTousLesTrajets());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un trajet")
    public ResponseEntity<TrajetDTO> mettreAJourTrajet(@PathVariable Long id, @RequestBody TrajetDTO dto) {
        return ResponseEntity.ok(trajetService.mettreAJourTrajet(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Annuler un trajet")
    public ResponseEntity<Void> supprimerTrajet(@PathVariable Long id) {
        trajetService.supprimerTrajet(id);
        return ResponseEntity.noContent().build();
    }
}
