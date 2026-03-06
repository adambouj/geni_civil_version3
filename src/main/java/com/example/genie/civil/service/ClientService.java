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

        if (clientRepository.findByNomClient(dto.getNomClient()).isPresent()) {
            throw new RuntimeException("Client déjà existant");
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

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}