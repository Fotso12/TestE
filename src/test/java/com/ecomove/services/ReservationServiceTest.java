package com.ecomove.services;

import com.ecomove.dtos.ReservationDTO;
import com.ecomove.entities.Reservation;
import com.ecomove.entities.Trajet;
import com.ecomove.entities.Utilisateur;
import com.ecomove.mapper.ReservationMapper;
import com.ecomove.repositories.ReservationRepository;
import com.ecomove.repositories.TrajetRepository;
import com.ecomove.repositories.UtilisateurRepository;
import com.ecomove.services.impl.ReservationServiceImpl;
import com.ecomove.utils.StatutReservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Tests unitaires pour le service de gestion des réservations.
 *
 * @author Darryl
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private UtilisateurRepository utilisateurRepository;
    @Mock
    private TrajetRepository trajetRepository;
    @Mock
    private ReservationMapper reservationMapper;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    private Reservation reservation;
    private ReservationDTO reservationDTO;
    private Utilisateur passager;
    private Trajet trajet;

    @BeforeEach
    void setUp() {
        passager = Utilisateur.builder().id(2L).nom("Passager").build();
        trajet = Trajet.builder().id(10L).placesDisponibles(5).build();
        
        reservation = Reservation.builder()
                .id(1L)
                .statut(StatutReservation.EN_ATTENTE)
                .passager(passager)
                .trajet(trajet)
                .build();

        reservationDTO = ReservationDTO.builder()
                .id(1L)
                .statut(StatutReservation.EN_ATTENTE)
                .passagerId(2L)
                .trajetId(10L)
                .build();
    }

    @Test
    void testReserverTrajet() {
        when(utilisateurRepository.findById(2L)).thenReturn(Optional.of(passager));
        when(trajetRepository.findById(10L)).thenReturn(Optional.of(trajet));
        when(reservationMapper.toEntity(any(ReservationDTO.class), any(), any())).thenReturn(reservation);
        when(reservationRepository.save(any())).thenReturn(reservation);
        when(reservationMapper.toDTO(any())).thenReturn(reservationDTO);

        ReservationDTO result = reservationService.reserverTrajet(reservationDTO);

        assertNotNull(result);
        verify(trajetRepository, times(1)).save(any()); // Vérifier la décrémentation des places
        verify(reservationRepository, times(1)).save(any());
    }
}
