package com.ecomove.services.impl;

import com.ecomove.dtos.ReservationDTO;
import com.ecomove.entities.Reservation;
import com.ecomove.entities.Trajet;
import com.ecomove.entities.Utilisateur;
import com.ecomove.mapper.ReservationMapper;
import com.ecomove.repositories.ReservationRepository;
import com.ecomove.repositories.TrajetRepository;
import com.ecomove.repositories.UtilisateurRepository;
import com.ecomove.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implémentation du service Reservation.
 *
 * @author Darryl
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final TrajetRepository trajetRepository;
    private final ReservationMapper reservationMapper;

    @Override
    public ReservationDTO reserverTrajet(ReservationDTO reservationDTO) {
        Utilisateur passager = utilisateurRepository.findById(reservationDTO.getPassagerId())
                .orElseThrow(() -> new RuntimeException("Passager non trouvé"));
        Trajet trajet = trajetRepository.findById(reservationDTO.getTrajetId())
                .orElseThrow(() -> new RuntimeException("Trajet non trouvé"));

        if (trajet.getPlacesDisponibles() <= 0) {
            throw new RuntimeException("Plus de places disponibles pour ce trajet");
        }

        Reservation reservation = reservationMapper.toEntity(reservationDTO, passager, trajet);
        reservation.setDateReservation(LocalDateTime.now());
        
        // Décrémenter les places sur le trajet
        trajet.setPlacesDisponibles(trajet.getPlacesDisponibles() - 1);
        trajetRepository.save(trajet);

        Reservation sauve = reservationRepository.save(reservation);
        return reservationMapper.toDTO(sauve);
    }

    @Override
    @Transactional(readOnly = true)
    public ReservationDTO obtenirReservationParId(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée"));
        return reservationMapper.toDTO(reservation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservationDTO> obtenirToutesLesReservations() {
        return reservationRepository.findAll().stream()
                .map(reservationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDTO mettreAJourReservation(Long id, ReservationDTO reservationDTO) {
        Reservation reservationExistante = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée"));
        
        reservationExistante.setStatut(reservationDTO.getStatut());
        
        Reservation miseAJour = reservationRepository.save(reservationExistante);
        return reservationMapper.toDTO(miseAJour);
    }

    @Override
    public void annulerReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée"));
        
        // Libérer la place sur le trajet
        Trajet trajet = reservation.getTrajet();
        trajet.setPlacesDisponibles(trajet.getPlacesDisponibles() + 1);
        trajetRepository.save(trajet);
        
        reservationRepository.delete(reservation);
    }
}
