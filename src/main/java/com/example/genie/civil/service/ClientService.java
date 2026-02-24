package com.example.genie.civil.service;

import com.example.genie.civil.dto.ClientDTO;
import com.example.genie.civil.entity.Client;
import com.example.genie.civil.mapper.EntityMapper;
import com.example.genie.civil.repository.ClientRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;
    private final EntityMapper entityMapper;

    public ClientService(ClientRepository clientRepository,
                         EntityMapper entityMapper) {
        this.clientRepository = clientRepository;
        this.entityMapper = entityMapper;
    }

    public List<ClientDTO> findAll() {
        return clientRepository.findAll()
                .stream()
                .map(entityMapper::clientToDto)
                .toList();
    }

    public ClientDTO findById(Long id) {
        return clientRepository.findById(id)
                .map(entityMapper::clientToDto)
                .orElseThrow(() ->
                        new RuntimeException("Client non trouvé avec l'ID: " + id));
    }

    public ClientDTO findByName(String name) {
        return clientRepository.findByNomClient(name)
                .map(entityMapper::clientToDto)
                .orElseThrow(() ->
                        new RuntimeException("Client non trouvé avec le nom: " + name));
    }

    public ClientDTO save(ClientDTO dto) {
        Client entity = entityMapper.clientToEntity(dto);
        return entityMapper.clientToDto(
                clientRepository.save(entity)
        );
    }

    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client non trouvé avec l'ID: " + id);
        }
        clientRepository.deleteById(id);
    }
}