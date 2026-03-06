package com.example.genie.civil.mapper;

import com.example.genie.civil.dto.PlanificationDTO;
import com.example.genie.civil.entity.Planification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlanificationMapper {

    @Mapping(source = "utilisateur.idUtilisateur", target = "idUtilisateur")
    @Mapping(source = "client.idClient", target = "idClient")
    @Mapping(source = "vehicule.idVehicule", target = "idVehicule")
    @Mapping(source = "creePar.idUtilisateur", target = "creeParId")
    PlanificationDTO toDTO(Planification planification);

    @Mapping(target = "utilisateur", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "vehicule", ignore = true)
    @Mapping(target = "creePar", ignore = true)
    Planification toEntity(PlanificationDTO dto);

    List<PlanificationDTO> toDTOList(List<Planification> planifications);
}