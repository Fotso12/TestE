package com.ecomove.dtos;

import com.ecomove.utils.StatutReservation;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Data Transfer Object pour une réservation.
 *
 * @author Darryl
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDTO {
    private Long id;
    private LocalDateTime dateReservation;
    private StatutReservation statut;
    private Long passagerId;
    private Long trajetId;
}
