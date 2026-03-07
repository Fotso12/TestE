package com.ecomove.controllers;

import com.ecomove.dtos.UtilisateurDTO;
import com.ecomove.services.UtilisateurService;
import com.ecomove.utils.RoleUtilisateur;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests d'intégration pour le contrôleur des utilisateurs.
 *
 * @author Darryl
 * @version 1.0
 */
@WebMvcTest(UtilisateurController.class)
public class UtilisateurControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UtilisateurService utilisateurService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testObtenirTousLesUtilisateurs() throws Exception {
        when(utilisateurService.obtenirTousLesUtilisateurs()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/utilisateurs"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreerUtilisateur() throws Exception {
        UtilisateurDTO dto = UtilisateurDTO.builder()
                .nom("Dupont")
                .prenom("Jean")
                .email("jean@ecomove.com")
                .role(RoleUtilisateur.PASSAGER)
                .build();

        when(utilisateurService.creerUtilisateur(any(UtilisateurDTO.class))).thenReturn(dto);

        mockMvc.perform(post("/api/utilisateurs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Dupont"));
    }
}
