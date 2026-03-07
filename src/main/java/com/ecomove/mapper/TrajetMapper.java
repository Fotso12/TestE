package com.ecomove.mapper;

import com.ecomove.dtos.TrajetDTO;
import com.ecomove.entities.Trajet;
import com.ecomove.entities.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Interface Mapper MapStruct pour l'entité Trajet.
 *
 * @author Darryl
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface TrajetMapper {

    /**
     * Convertit une entité Trajet en TrajetDTO.
     * @param trajet L'entité à convertir.
     * @return Le DTO correspondant.
     */
    @Mapping(source = "conducteur.id", target = "conducteurId")
    TrajetDTO toDTO(Trajet trajet);

    /**
     * Convertit un TrajetDTO en entité Trajet.
     * @param dto Le DTO à convertir.
     * @param conducteur Le conducteur associé (passé en paramètre pour le lien).
     * @return L'entité correspondante.
     */
    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "reservations", ignore = true)
    @Mapping(target = "conducteur", source = "conducteur")
    Trajet toEntity(TrajetDTO dto, Utilisateur conducteur);
}
