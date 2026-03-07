package com.ecomove.dtos;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Data Transfer Object pour un trajet.
 *
 * @author Darryl
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrajetDTO {
    private Long id;
    private String pointDepart;
    private String pointArrivee;
    private LocalDateTime dateHeureDepart;
    private Integer placesDisponibles;
    private Double prix;
    private Double consommationCO2;
    private Long conducteurId;
}
