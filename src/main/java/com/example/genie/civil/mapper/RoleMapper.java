package com.example.genie.civil.mapper;

import com.example.genie.civil.dto.RoleDTO;
import com.example.genie.civil.entity.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDTO toDTO(Role role);

    Role toEntity(RoleDTO dto);

    List<RoleDTO> toDTOList(List<Role> roles);

    List<Role> toEntityList(List<RoleDTO> dtos);
}