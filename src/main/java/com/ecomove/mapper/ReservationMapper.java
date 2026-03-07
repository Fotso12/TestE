package com.ecomove.mapper;

import com.ecomove.dtos.ReservationDTO;
import com.ecomove.entities.Reservation;
import com.ecomove.entities.Trajet;
import com.ecomove.entities.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Interface Mapper MapStruct pour l'entité Reservation.
 *
 * @author Darryl
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface ReservationMapper {

    /**
     * Convertit une entité Reservation en ReservationDTO.
     * @param reservation L'entité à convertir.
     * @return Le DTO correspondant.
     */
    @Mapping(source = "passager.id", target = "passagerId")
    @Mapping(source = "trajet.id", target = "trajetId")
    ReservationDTO toDTO(Reservation reservation);

    /**
     * Convertit un ReservationDTO en entité Reservation.
     * @param dto Le DTO à convertir.
     * @param passager Le passager associé.
     * @param trajet Le trajet associé.
     * @return L'entité correspondante.
     */
    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "passager", source = "passager")
    @Mapping(target = "trajet", source = "trajet")
    Reservation toEntity(ReservationDTO dto, Utilisateur passager, Trajet trajet);
}
