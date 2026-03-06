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

    public FichierDTO create(FichierDTO dto) {

        Fichier fichier = fichierMapper.toEntity(dto);

        MissionTerrain mission = missionRepository.findById(dto.getMissionId())
                .orElseThrow(() -> new RuntimeException("Mission introuvable"));

        fichier.setMission(mission);

        Fichier saved = fichierRepository.save(fichier);

        return fichierMapper.toDTO(saved);
    }

    public List<FichierDTO> findByMission(Long missionId) {
        return fichierMapper.toDTOList(
                fichierRepository.findByMission_IdMission(missionId)
        );
    }
}