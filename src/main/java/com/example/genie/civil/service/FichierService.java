package com.example.genie.civil.service;

import com.example.genie.civil.dto.FichierDTO;
import com.example.genie.civil.entity.Fichier;
import com.example.genie.civil.entity.MissionTerrain;
import com.example.genie.civil.mapper.FichierMapper;
import com.example.genie.civil.repository.FichierRepository;
import com.example.genie.civil.repository.MissionTerrainRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FichierService {

    private final FichierRepository fichierRepository;
    private final MissionTerrainRepository missionRepository;
    private final FichierMapper fichierMapper;

    public FichierService(FichierRepository fichierRepository,
                          MissionTerrainRepository missionRepository,
                          FichierMapper fichierMapper) {
        this.fichierRepository = fichierRepository;
        this.missionRepository = missionRepository;
        this.fichierMapper = fichierMapper;
    }

    // CREATE
    public FichierDTO create(FichierDTO dto) {
        Fichier fichier = fichierMapper.toEntity(dto);

        MissionTerrain mission = missionRepository.findById(dto.getMissionId())
                .orElseThrow(() -> new RuntimeException("Mission introuvable"));
        fichier.setMission(mission);

        Fichier saved = fichierRepository.save(fichier);
        return fichierMapper.toDTO(saved);
    }

    // READ ALL by mission
    public List<FichierDTO> findByMission(Long missionId) {
        return fichierMapper.toDTOList(
                fichierRepository.findByMission_IdMission(missionId)
        );
    }

    // READ single by ID
    public FichierDTO findById(Long id) {
        Fichier fichier = fichierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fichier introuvable"));
        return fichierMapper.toDTO(fichier);
    }

    // UPDATE
    public FichierDTO update(Long id, FichierDTO dto) {
        Fichier fichier = fichierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fichier introuvable"));

        fichier.setTypeFichier(dto.getTypeFichier());
        fichier.setUrl(dto.getUrl());

        if (dto.getMissionId() != null) {
            MissionTerrain mission = missionRepository.findById(dto.getMissionId())
                    .orElseThrow(() -> new RuntimeException("Mission introuvable"));
            fichier.setMission(mission);
        }

        Fichier updated = fichierRepository.save(fichier);
        return fichierMapper.toDTO(updated);
    }

    // DELETE
    public void delete(Long id) {
        Fichier fichier = fichierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fichier introuvable"));
        fichierRepository.delete(fichier);
    }
}