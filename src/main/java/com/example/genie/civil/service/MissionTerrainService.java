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

        if (missionRepository.findByNumeroIntervention(dto.getNumeroIntervention()).isPresent()) {
            throw new RuntimeException("Numéro d'intervention déjà existant");
        }

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

    public MissionTerrainDTO findById(Long id) {

        MissionTerrain mission = missionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mission non trouvée"));

        return missionMapper.toDTO(mission);
    }

    public List<MissionTerrainDTO> findByUtilisateur(Long idUtilisateur) {
        return missionMapper.toDTOList(
                missionRepository.findByUtilisateur_IdUtilisateur(idUtilisateur)
        );
    }

    public List<MissionTerrainDTO> findByClient(Long idClient) {
        return missionMapper.toDTOList(
                missionRepository.findByClient_IdClient(idClient)
        );
    }

    public MissionTerrainDTO update(Long id, MissionTerrainDTO dto) {

        MissionTerrain mission = missionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mission non trouvée"));

        Utilisateur utilisateur = utilisateurRepository.findById(dto.getIdUtilisateur())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        Client client = clientRepository.findById(dto.getIdClient())
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        Vehicule vehicule = vehiculeRepository.findById(dto.getIdVehicule())
                .orElseThrow(() -> new RuntimeException("Vehicule introuvable"));


        mission.setUtilisateur(utilisateur);
        mission.setClient(client);
        mission.setVehicule(vehicule);

        mission.setNumeroIntervention(dto.getNumeroIntervention());
        mission.setTypeIntervention(dto.getTypeIntervention());
        mission.setDateHeureDebut(dto.getDateHeureDebut());
        mission.setDateHeureFin(dto.getDateHeureFin());
        mission.setStatut(dto.getStatut());
        mission.setDescription(dto.getDescription());
        mission.setObservations(dto.getObservations());

        if (dto.getDateHeureDebut() != null && dto.getDateHeureFin() != null) {

            int duree = (int) Duration.between(
                    dto.getDateHeureDebut(),
                    dto.getDateHeureFin()
            ).toMinutes();

            mission.setDureeTotale(duree);
        }

        MissionTerrain updated = missionRepository.save(mission);

        return missionMapper.toDTO(updated);
    }

    public void delete(Long id) {

        MissionTerrain mission = missionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mission non trouvée"));

        missionRepository.delete(mission);
    }
}