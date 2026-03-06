package com.example.genie.civil.mapper;

import com.example.genie.civil.dto.PointageDTO;
import com.example.genie.civil.entity.Pointage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PointageMapper {

    @Mapping(source = "utilisateur.idUtilisateur", target = "idUtilisateur")
    PointageDTO toDTO(Pointage pointage);

    @Mapping(target = "utilisateur", ignore = true)
    Pointage toEntity(PointageDTO dto);

    List<PointageDTO> toDTOList(List<Pointage> pointages);
}