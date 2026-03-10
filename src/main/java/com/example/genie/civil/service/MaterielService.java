package com.example.genie.civil.service;

import com.example.genie.civil.dto.MaterielDTO;
import com.example.genie.civil.entity.Materiel;
import com.example.genie.civil.mapper.MaterielMapper;
import com.example.genie.civil.repository.MaterielRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterielService {

    private final MaterielRepository materielRepository;
    private final MaterielMapper materielMapper;

    public MaterielService(MaterielRepository materielRepository,
                           MaterielMapper materielMapper) {
        this.materielRepository = materielRepository;
        this.materielMapper = materielMapper;
    }

    public MaterielDTO create(MaterielDTO dto) {

        if (materielRepository.existsByReference(dto.getReference())) {
            throw new RuntimeException("Matériel avec cette référence existe déjà");
        }

        Materiel materiel = materielMapper.toEntity(dto);
        Materiel saved = materielRepository.save(materiel);

        return materielMapper.toDTO(saved);
    }

    public List<MaterielDTO> findAll() {
        return materielMapper.toDTOList(materielRepository.findAll());
    }

    public MaterielDTO findById(Long id) {
        Materiel materiel = materielRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matériel non trouvé"));

        return materielMapper.toDTO(materiel);
    }

    public MaterielDTO update(Long id, MaterielDTO dto) {

        Materiel materiel = materielRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matériel non trouvé"));

        materiel.setNom(dto.getNom());
        materiel.setReference(dto.getReference());
        materiel.setEtat(dto.getEtat());

        Materiel updated = materielRepository.save(materiel);

        return materielMapper.toDTO(updated);
    }

    public void delete(Long id) {
        Materiel materiel = materielRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matériel non trouvé"));

        materielRepository.delete(materiel);
    }
}