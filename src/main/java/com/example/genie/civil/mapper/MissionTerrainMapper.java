package com.example.genie.civil.mapper;

import com.example.genie.civil.dto.MissionTerrainDTO;
import com.example.genie.civil.entity.MissionTerrain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MissionTerrainMapper {

    @Mapping(source = "utilisateur.idUtilisateur", target = "idUtilisateur")
    @Mapping(source = "client.idClient", target = "idClient")
    @Mapping(source = "vehicule.idVehicule", target = "idVehicule")
    MissionTerrainDTO toDTO(MissionTerrain mission);

    @Mapping(target = "utilisateur", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "vehicule", ignore = true)
    @Mapping(target = "fichiers", ignore = true)
    MissionTerrain toEntity(MissionTerrainDTO dto);

    List<MissionTerrainDTO> toDTOList(List<MissionTerrain> missions);
}