package com.example.genie.civil.mapper;

import com.example.genie.civil.dto.MaterielDTO;
import com.example.genie.civil.entity.Materiel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MaterielMapper {

    MaterielDTO toDTO(Materiel materiel);

    Materiel toEntity(MaterielDTO dto);

    List<MaterielDTO> toDTOList(List<Materiel> materiels);

    List<Materiel> toEntityList(List<MaterielDTO> dtos);
}