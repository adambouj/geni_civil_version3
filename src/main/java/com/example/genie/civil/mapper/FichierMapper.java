package com.example.genie.civil.mapper;

import com.example.genie.civil.dto.FichierDTO;
import com.example.genie.civil.entity.Fichier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FichierMapper {

    @Mapping(source = "mission.idMission", target = "missionId")
    FichierDTO toDTO(Fichier fichier);

    @Mapping(target = "mission", ignore = true)
    Fichier toEntity(FichierDTO dto);

    List<FichierDTO> toDTOList(List<Fichier> fichiers);
}