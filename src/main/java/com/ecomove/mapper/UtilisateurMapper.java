package com.ecomove.mapper;

import com.ecomove.dtos.UtilisateurDTO;
import com.ecomove.entities.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Interface Mapper MapStruct pour l'entité Utilisateur.
 *
 * @author Darryl
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface UtilisateurMapper {

    /**
     * Convertit une entité Utilisateur en UtilisateurDTO.
     * @param utilisateur L'entité à convertir.
     * @return Le DTO correspondant.
     */
    UtilisateurDTO toDTO(Utilisateur utilisateur);

    /**
     * Convertit un UtilisateurDTO en entité Utilisateur.
     * @param dto Le DTO à convertir.
     * @return L'entité correspondante.
     */
    @Mapping(target = "motDePasse", ignore = true)
    @Mapping(target = "trajetsProposes", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    Utilisateur toEntity(UtilisateurDTO dto);
}
