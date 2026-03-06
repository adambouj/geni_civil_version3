package com.example.genie.civil.mapper;

import com.example.genie.civil.dto.VehiculeDTO;
import com.example.genie.civil.entity.Vehicule;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehiculeMapper {

    VehiculeDTO toDTO(Vehicule vehicule);

    Vehicule toEntity(VehiculeDTO dto);

    List<VehiculeDTO> toDTOList(List<Vehicule> vehicules);

    List<Vehicule> toEntityList(List<VehiculeDTO> dtos);
}