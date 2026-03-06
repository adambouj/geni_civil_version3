package com.example.genie.civil.mapper;

import com.example.genie.civil.dto.ClientDTO;
import com.example.genie.civil.entity.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDTO toDTO(Client client);

    Client toEntity(ClientDTO dto);

    List<ClientDTO> toDTOList(List<Client> clients);

    List<Client> toEntityList(List<ClientDTO> dtos);
}