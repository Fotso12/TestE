package com.ecomove.services;

import com.ecomove.dtos.UtilisateurDTO;
import com.ecomove.entities.Utilisateur;
import com.ecomove.mapper.UtilisateurMapper;
import com.ecomove.repositories.UtilisateurRepository;
import com.ecomove.services.impl.UtilisateurServiceImpl;
import com.ecomove.utils.RoleUtilisateur;
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
 * Tests unitaires pour le service de gestion des utilisateurs.
 *
 * @author Darryl
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
public class UtilisateurServiceTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private UtilisateurMapper utilisateurMapper;

    @InjectMocks
    private UtilisateurServiceImpl utilisateurService;

    private Utilisateur utilisateur;
    private UtilisateurDTO utilisateurDTO;

    @BeforeEach
    void setUp() {
        utilisateur = Utilisateur.builder()
                .id(1L)
                .nom("Dupont")
                .prenom("Jean")
                .email("jean@ecomove.com")
                .role(RoleUtilisateur.PASSAGER)
                .build();

        utilisateurDTO = UtilisateurDTO.builder()
                .id(1L)
                .nom("Dupont")
                .prenom("Jean")
                .email("jean@ecomove.com")
                .role(RoleUtilisateur.PASSAGER)
                .build();
    }

    @Test
    void testCreerUtilisateur() {
        when(utilisateurMapper.toEntity(any(UtilisateurDTO.class))).thenReturn(utilisateur);
        when(utilisateurRepository.save(any(Utilisateur.class))).thenReturn(utilisateur);
        when(utilisateurMapper.toDTO(any(Utilisateur.class))).thenReturn(utilisateurDTO);

        UtilisateurDTO result = utilisateurService.creerUtilisateur(utilisateurDTO);

        assertNotNull(result);
        assertEquals("jean@ecomove.com", result.getEmail());
        verify(utilisateurRepository, times(1)).save(any(Utilisateur.class));
    }

    @Test
    void testObtenirUtilisateurParId() {
        when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(utilisateur));
        when(utilisateurMapper.toDTO(utilisateur)).thenReturn(utilisateurDTO);

        UtilisateurDTO result = utilisateurService.obtenirUtilisateurParId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }
}
