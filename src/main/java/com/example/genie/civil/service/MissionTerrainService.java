package com.example.genie.civil.service;

import com.example.genie.civil.dto.MissionTerrainDTO;
import com.example.genie.civil.entity.*;
import com.example.genie.civil.mapper.MissionTerrainMapper;
import com.example.genie.civil.repository.*;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class MissionTerrainService {

    private final MissionTerrainRepository missionRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ClientRepository clientRepository;
    private final VehiculeRepository vehiculeRepository;
    private final MissionTerrainMapper missionMapper;

    public MissionTerrainService(MissionTerrainRepository missionRepository,
                                 UtilisateurRepository utilisateurRepository,
                                 ClientRepository clientRepository,
                                 VehiculeRepository vehiculeRepository,
                                 MissionTerrainMapper missionMapper) {
        this.missionRepository = missionRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.clientRepository = clientRepository;
        this.vehiculeRepository = vehiculeRepository;
        this.missionMapper = missionMapper;
    }

    public MissionTerrainDTO create(MissionTerrainDTO dto) {

        MissionTerrain mission = missionMapper.toEntity(dto);

        Utilisateur utilisateur = utilisateurRepository.findById(dto.getIdUtilisateur())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        Client client = clientRepository.findById(dto.getIdClient())
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        Vehicule vehicule = vehiculeRepository.findById(dto.getIdVehicule())
                .orElseThrow(() -> new RuntimeException("Vehicule introuvable"));

        mission.setUtilisateur(utilisateur);
        mission.setClient(client);
        mission.setVehicule(vehicule);
        // 🔹 Calculate duration automatically
        if (dto.getDateHeureDebut() != null && dto.getDateHeureFin() != null) {
            int duree = (int) Duration.between(
                    dto.getDateHeureDebut(),
                    dto.getDateHeureFin()
            ).toMinutes();

            mission.setDureeTotale(duree);
        }

        MissionTerrain saved = missionRepository.save(mission);

        return missionMapper.toDTO(saved);
    }

    public List<MissionTerrainDTO> findAll() {
        return missionMapper.toDTOList(missionRepository.findAll());
    }
}