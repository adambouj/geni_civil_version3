package com.example.genie.civil.service;

import com.example.genie.civil.dto.VehiculeDTO;
import com.example.genie.civil.entity.Vehicule;
import com.example.genie.civil.mapper.VehiculeMapper;
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
    private final VehiculeMapper vehiculeMapper;

    // GET ALL
    @Transactional(readOnly = true)
    public List<VehiculeDTO> findAll() {
        return vehiculeRepository.findAll()
                .stream()
                .map(vehiculeMapper::toDTO)
                .toList();
    }

    // GET BY ID
    @Transactional(readOnly = true)
    public VehiculeDTO findById(Long id) {
        return vehiculeRepository.findById(id)
                .map(vehiculeMapper::toDTO)
                .orElseThrow(() ->
                        new RuntimeException("Vehicule non trouvé avec l'ID: " + id));
    }

    // GET BY IMMATRICULATION
    @Transactional(readOnly = true)
    public VehiculeDTO findByImmatriculation(String immatriculation) {
        return vehiculeRepository.findByImmatriculation(immatriculation)
                .map(vehiculeMapper::toDTO)
                .orElseThrow(() ->
                        new RuntimeException("Vehicule non trouvé avec l'immatriculation: " + immatriculation));
    }

    // CREATE
    public VehiculeDTO create(VehiculeDTO dto) {
        if (dto.getImmatriculation() != null &&
                vehiculeRepository.findByImmatriculation(dto.getImmatriculation()).isPresent()) {
            throw new RuntimeException("Vehicule avec cette immatriculation existe déjà");
        }

        Vehicule entity = vehiculeMapper.toEntity(dto);
        Vehicule saved = vehiculeRepository.save(entity);
        return vehiculeMapper.toDTO(saved);
    }

    // UPDATE
    public VehiculeDTO update(Long id, VehiculeDTO dto) {
        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicule non trouvé avec l'ID: " + id));

        vehicule.setNumeroParc(dto.getNumeroParc());
        vehicule.setImmatriculation(dto.getImmatriculation());
        vehicule.setEtat(dto.getEtat());

        Vehicule updated = vehiculeRepository.save(vehicule);
        return vehiculeMapper.toDTO(updated);
    }

    // DELETE
    public void delete(Long id) {
        if (!vehiculeRepository.existsById(id)) {
            throw new RuntimeException("Vehicule non trouvé avec l'ID: " + id);
        }
        vehiculeRepository.deleteById(id);
    }
}