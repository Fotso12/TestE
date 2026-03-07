package com.ecomove.services;

import com.ecomove.dtos.TrajetDTO;
import com.ecomove.entities.Trajet;
import com.ecomove.entities.Utilisateur;
import com.ecomove.mapper.TrajetMapper;
import com.ecomove.repositories.TrajetRepository;
import com.ecomove.repositories.UtilisateurRepository;
import com.ecomove.services.impl.TrajetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Tests unitaires pour le service de gestion des trajets.
 *
 * @author Darryl
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
public class TrajetServiceTest {

    @Mock
    private TrajetRepository trajetRepository;

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private TrajetMapper trajetMapper;

    @InjectMocks
    private TrajetServiceImpl trajetService;

    private Trajet trajet;
    private TrajetDTO trajetDTO;
    private Utilisateur conducteur;

    @BeforeEach
    void setUp() {
        conducteur = Utilisateur.builder().id(1L).nom("Dupont").email("jean@test.com").build();
        
        trajet = Trajet.builder()
                .id(1L)
                .pointDepart("Paris")
                .pointArrivee("Lyon")
                .consommationCO2(15.5)
                .conducteur(conducteur)
                .build();

        trajetDTO = TrajetDTO.builder()
                .id(1L)
                .pointDepart("Paris")
                .pointArrivee("Lyon")
                .consommationCO2(15.5)
                .conducteurId(1L)
                .build();
    }

    @Test
    void testCreerTrajet() {
        when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(conducteur));
        when(trajetMapper.toEntity(any(TrajetDTO.class), any(Utilisateur.class))).thenReturn(trajet);
        when(trajetRepository.save(any(Trajet.class))).thenReturn(trajet);
        when(trajetMapper.toDTO(any(Trajet.class))).thenReturn(trajetDTO);

        TrajetDTO result = trajetService.creerTrajet(trajetDTO);

        assertNotNull(result);
        assertEquals("Paris", result.getPointDepart());
        assertEquals(15.5, result.getConsommationCO2());
        verify(trajetRepository, times(1)).save(any(Trajet.class));
    }

    @Test
    void testObtenirTrajetParId() {
        when(trajetRepository.findById(1L)).thenReturn(Optional.of(trajet));
        when(trajetMapper.toDTO(trajet)).thenReturn(trajetDTO);

        TrajetDTO result = trajetService.obtenirTrajetParId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }
}
