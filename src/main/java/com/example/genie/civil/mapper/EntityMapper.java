package com.example.genie.civil.mapper;

import com.example.genie.civil.dto.*;
import com.example.genie.civil.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EntityMapper {

    /* =======================
       UTILISATEUR
       ======================= */
    default UtilisateurDTO utilisateurToDto(Utilisateur utilisateur) {
        if (utilisateur == null) return null;

        UtilisateurDTO dto = new UtilisateurDTO();
        dto.setIdUtilisateur(utilisateur.getIdUtilisateur());
        dto.setNom(utilisateur.getNom());
        dto.setPrenom(utilisateur.getPrenom());
        dto.setEmail(utilisateur.getEmail());
        dto.setMotDePasse(utilisateur.getMotDePasse());
        dto.setActif(utilisateur.getActif());
        dto.setDateCreation(utilisateur.getDateCreation());

        // Convert Roles to roleIds
        if (utilisateur.getRoles() != null) {
            dto.setRoleIds(
                    utilisateur.getRoles()
                            .stream()
                            .map(Role::getIdRole)
                            .collect(Collectors.toSet())
            );
        }
        return dto;
    }

    @Mapping(target = "roles", ignore = true) // handled in service
    Utilisateur utilisateurToEntity(UtilisateurDTO dto);


    /* =======================
       ROLE
       ======================= */
    RoleDTO roleToDto(Role entity);
    Role roleToEntity(RoleDTO dto);


    /* =======================
       CLIENT
       ======================= */
    ClientDTO clientToDto(Client entity);
    Client clientToEntity(ClientDTO dto);
    List<ClientDTO> clientToDtoList(List<Client> entities);
    List<Client> clientToEntityList(List<ClientDTO> dtos);


    /* =======================
       VEHICULE
       ======================= */
    VehiculeDTO vehiculeToDto(Vehicule entity);
    Vehicule vehiculeToEntity(VehiculeDTO dto);


    /* =======================
       PLANIFICATION
       ======================= */
    @Mapping(source = "utilisateur.idUtilisateur", target = "idUtilisateur")
    @Mapping(source = "client.idClient", target = "idClient")
    @Mapping(source = "vehicule.idVehicule", target = "idVehicule")
    PlanificationDTO planificationToDto(Planification entity);

    @Mapping(target = "utilisateur", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "vehicule", ignore = true)
    Planification planificationToEntity(PlanificationDTO dto);


    /* =======================
       MISSION TERRAIN
       ======================= */
    @Mapping(source = "utilisateur.idUtilisateur", target = "idUtilisateur")
    @Mapping(source = "client.idClient", target = "idClient")
    @Mapping(source = "vehicule.idVehicule", target = "idVehicule")
    MissionTerrainDTO missionTerrainToDto(MissionTerrain entity);

    @Mapping(target = "utilisateur", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "vehicule", ignore = true)
    MissionTerrain missionTerrainToEntity(MissionTerrainDTO dto);

    List<MissionTerrainDTO> missionTerrainToDtoList(List<MissionTerrain> entities);
    List<MissionTerrain> missionTerrainToEntityList(List<MissionTerrainDTO> dtos);


    /* =======================
       POINTAGE
       ======================= */
    @Mapping(source = "utilisateur.idUtilisateur", target = "idUtilisateur")
    PointageDTO pointageToDto(Pointage entity);

    @Mapping(target = "utilisateur", ignore = true)
    Pointage pointageToEntity(PointageDTO dto);
}