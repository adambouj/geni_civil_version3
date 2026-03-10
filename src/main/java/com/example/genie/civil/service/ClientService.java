package com.example.genie.civil.service;

import com.example.genie.civil.dto.ClientDTO;
import com.example.genie.civil.entity.Client;
import com.example.genie.civil.mapper.ClientMapper;
import com.example.genie.civil.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository,
                         ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public ClientDTO create(ClientDTO dto) {

        if (clientRepository.findBySiret(dto.getSiret()).isPresent()) {
            throw new RuntimeException("Client avec ce SIRET existe déjà");
        }

        Client client = clientMapper.toEntity(dto);
        Client saved = clientRepository.save(client);

        return clientMapper.toDTO(saved);
    }

    public List<ClientDTO> findAll() {
        return clientMapper.toDTOList(clientRepository.findAll());
    }

    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        return clientMapper.toDTO(client);
    }

    public ClientDTO update(Long id, ClientDTO dto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        client.setNomClient(dto.getNomClient());
        client.setSiret(dto.getSiret());
        client.setAdresse(dto.getAdresse());
        client.setTelephone(dto.getTelephone());
        client.setEmail(dto.getEmail());

        Client updated = clientRepository.save(client);

        return clientMapper.toDTO(updated);
    }

    public void delete(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        clientRepository.delete(client);
    }
}