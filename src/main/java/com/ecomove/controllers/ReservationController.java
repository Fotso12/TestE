package com.ecomove.controllers;

import com.ecomove.dtos.ReservationDTO;
import com.ecomove.services.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour la gestion des réservations.
 *
 * @author Darryl
 * @version 1.0
 */
@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
@Tag(name = "Reservation", description = "API de gestion des réservations de places")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    @Operation(summary = "Réserver un trajet", description = "Permet à un passager de réserver une place sur un trajet")
    public ResponseEntity<ReservationDTO> reserverTrajet(@RequestBody ReservationDTO dto) {
        return ResponseEntity.ok(reservationService.reserverTrajet(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir les détails d'une réservation")
    public ResponseEntity<ReservationDTO> obtenirReservationParId(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.obtenirReservationParId(id));
    }

    @GetMapping
    @Operation(summary = "Lister toutes les réservations")
    public ResponseEntity<List<ReservationDTO>> obtenirToutesLesReservations() {
        return ResponseEntity.ok(reservationService.obtenirToutesLesReservations());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour le statut d'une réservation")
    public ResponseEntity<ReservationDTO> mettreAJourReservation(@PathVariable Long id, @RequestBody ReservationDTO dto) {
        return ResponseEntity.ok(reservationService.mettreAJourReservation(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Annuler une réservation")
    public ResponseEntity<Void> annulerReservation(@PathVariable Long id) {
        reservationService.annulerReservation(id);
        return ResponseEntity.noContent().build();
    }
}
