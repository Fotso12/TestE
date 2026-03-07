package com.ecomove.controllers;

import com.ecomove.dtos.TrajetDTO;
import com.ecomove.services.TrajetService;
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
 * Tests d'intégration pour le contrôleur des trajets.
 *
 * @author Darryl
 * @version 1.0
 */
@WebMvcTest(TrajetController.class)
public class TrajetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrajetService trajetService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testObtenirTousLesTrajets() throws Exception {
        when(trajetService.obtenirTousLesTrajets()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/trajets"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCreerTrajet() throws Exception {
        TrajetDTO dto = TrajetDTO.builder()
                .pointDepart("Paris")
                .pointArrivee("Lyon")
                .consommationCO2(12.0)
                .conducteurId(1L)
                .build();

        when(trajetService.creerTrajet(any(TrajetDTO.class))).thenReturn(dto);

        mockMvc.perform(post("/api/trajets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pointDepart").value("Paris"))
                .andExpect(jsonPath("$.consommationCO2").value(12.0));
    }
}
