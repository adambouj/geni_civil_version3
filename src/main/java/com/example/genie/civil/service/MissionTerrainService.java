package com.example.genie.civil.service;

import com.example.genie.civil.dto.MissionTerrainDTO;
import com.example.genie.civil.entity.Client;
import com.example.genie.civil.entity.MissionTerrain;
import com.example.genie.civil.entity.Utilisateur;
import com.example.genie.civil.entity.Vehicule;
import com.example.genie.civil.mapper.EntityMapper;
import com.example.genie.civil.repository.ClientRepository;
import com.example.genie.civil.repository.MissionTerrainRepository;

import com.example.genie.civil.repository.UtilisateurRepository;
import com.example.genie.civil.repository.VehiculeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionTerrainService {

    private final MissionTerrainRepository missionTerrainRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ClientRepository clientRepository;
    private final VehiculeRepository vehiculeRepository;
    private final EntityMapper entityMapper;

    /* =========================
       FIND ALL
       ========================= */
    @Transactional(readOnly = true)
    public List<MissionTerrainDTO> findAll() {
        return missionTerrainRepository.findAll()
                .stream()
                .map(entityMapper::missionTerrainToDto)
                .toList();
    }

    /* =========================
       FIND BY ID
       ========================= */
    @Transactional(readOnly = true)
    public MissionTerrainDTO findById(Long id) {
        MissionTerrain entity = missionTerrainRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Mission non trouvée avec l'ID: " + id));

        return entityMapper.missionTerrainToDto(entity);
    }

    /* =========================
       SAVE
       ========================= */
    public MissionTerrainDTO save(MissionTerrainDTO dto) {
        // Convert DTO to entity (ignores relationships for now)
        MissionTerrain mission = entityMapper.missionTerrainToEntity(dto);

        // Fetch and set related entities
        if (dto.getIdUtilisateur() != null) {
            Utilisateur utilisateur = utilisateurRepository.findById(dto.getIdUtilisateur())
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé: " + dto.getIdUtilisateur()));
            mission.setUtilisateur(utilisateur);
        }

        if (dto.getIdClient() != null) {
            Client client = clientRepository.findById(dto.getIdClient())
                    .orElseThrow(() -> new RuntimeException("Client non trouvé: " + dto.getIdClient()));
            mission.setClient(client);
        }

        if (dto.getIdVehicule() != null) {
            Vehicule vehicule = vehiculeRepository.findById(dto.getIdVehicule())
                    .orElseThrow(() -> new RuntimeException("Vehicule non trouvé: " + dto.getIdVehicule()));
            mission.setVehicule(vehicule);
        }

        // Save mission
        MissionTerrain saved = missionTerrainRepository.save(mission);

        // Convert back to DTO
        return entityMapper.missionTerrainToDto(saved);
    }

    /* =========================
       DELETE
       ========================= */
    public void delete(Long id) {
        if (!missionTerrainRepository.existsById(id)) {
            throw new RuntimeException("Mission non trouvée avec l'ID: " + id);
        }
        missionTerrainRepository.deleteById(id);
    }
}