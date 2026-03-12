package com.example.genie.civil.mapper;

import com.example.genie.civil.dto.MissionTerrainDTO;
import com.example.genie.civil.entity.MissionTerrain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MissionTerrainMapper {

    @Mapping(source = "utilisateur.idUtilisateur", target = "idUtilisateur")
    @Mapping(source = "utilisateur.nom", target = "utilisateurNom")
    @Mapping(source = "utilisateur.prenom", target = "utilisateurPrenom")
    @Mapping(source = "client.idClient", target = "idClient")
    @Mapping(source = "client.nomClient", target = "clientNom")
    @Mapping(source = "vehicule.idVehicule", target = "idVehicule")
    @Mapping(source = "vehicule.immatriculation", target = "vehiculeImmatriculation")
    MissionTerrainDTO toDTO(MissionTerrain mission);

    @Mapping(target = "utilisateur", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "vehicule", ignore = true)
    @Mapping(target = "fichiers", ignore = true)
    MissionTerrain toEntity(MissionTerrainDTO dto);

    List<MissionTerrainDTO> toDTOList(List<MissionTerrain> missions);
}