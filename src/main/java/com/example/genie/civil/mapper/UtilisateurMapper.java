package com.example.genie.civil.mapper;

import com.example.genie.civil.dto.*;
import com.example.genie.civil.entity.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UtilisateurMapper {

    // Create DTO → Entity
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "idUtilisateur", ignore = true)
    @Mapping(target = "actif", ignore = true)
    @Mapping(target = "dateCreation", ignore = true)
    @Mapping(target = "planifications", ignore = true)
    @Mapping(target = "missions", ignore = true)
    @Mapping(target = "pointages", ignore = true)
    Utilisateur toEntity(UtilisateurCreateDTO dto);

    // Entity → Response DTO
    UtilisateurResponseDTO toResponseDTO(Utilisateur utilisateur);



    List<UtilisateurResponseDTO> toResponseDTOList(List<Utilisateur> utilisateurs);

// Les rôles seront injectés dans le Service (pas ici).
}