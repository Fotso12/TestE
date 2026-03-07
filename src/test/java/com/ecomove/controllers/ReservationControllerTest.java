package com.ecomove.controllers;

import com.ecomove.dtos.ReservationDTO;
import com.ecomove.services.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests d'intégration pour le contrôleur des réservations.
 *
 * @author Darryl
 * @version 1.0
 */
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testReserverTrajet() throws Exception {
        ReservationDTO dto = ReservationDTO.builder()
                .passagerId(1L)
                .trajetId(2L)
                .build();

        when(reservationService.reserverTrajet(any(ReservationDTO.class))).thenReturn(dto);

        mockMvc.perform(post("/api/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void testObtenirReservationParId() throws Exception {
        when(reservationService.obtenirReservationParId(1L)).thenReturn(new ReservationDTO());

        mockMvc.perform(get("/api/reservations/1"))
                .andExpect(status().isOk());
    }
}
