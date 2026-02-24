package com.example.genie.civil.service;

import com.example.genie.civil.dto.VehiculeDTO;
import com.example.genie.civil.entity.Vehicule;
import com.example.genie.civil.mapper.EntityMapper;
import com.example.genie.civil.repository.VehiculeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final EntityMapper entityMapper;

    @Transactional(readOnly = true)
    public List<VehiculeDTO> findAll() {
        return vehiculeRepository.findAll()
                .stream()
                .map(entityMapper::vehiculeToDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public VehiculeDTO findById(Long id) {
        return vehiculeRepository.findById(id)
                .map(entityMapper::vehiculeToDto)
                .orElseThrow(() ->
                        new RuntimeException("Vehicule non trouvé avec l'ID: " + id));
    }

    @Transactional(readOnly = true)
    public VehiculeDTO findByImmatriculation(String immatriculation) {
        return vehiculeRepository.findByImmatriculation(immatriculation)
                .map(entityMapper::vehiculeToDto)
                .orElseThrow(() ->
                        new RuntimeException("Vehicule non trouvé avec l'immatriculation: " + immatriculation));
    }

    public VehiculeDTO save(VehiculeDTO dto) {
        Vehicule entity = entityMapper.vehiculeToEntity(dto);
        Vehicule saved = vehiculeRepository.save(entity);
        return entityMapper.vehiculeToDto(saved);
    }

    public void delete(Long id) {
        if (!vehiculeRepository.existsById(id)) {
            throw new RuntimeException("Vehicule non trouvé avec l'ID: " + id);
        }
        vehiculeRepository.deleteById(id);
    }
}