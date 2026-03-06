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
        Materiel materiel = materielMapper.toEntity(dto);
        Materiel saved = materielRepository.save(materiel);
        return materielMapper.toDTO(saved);
    }

    public List<MaterielDTO> findAll() {
        return materielMapper.toDTOList(materielRepository.findAll());
    }

    public void delete(Long id) {
        materielRepository.deleteById(id);
    }
}