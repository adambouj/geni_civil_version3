package com.example.genie.civil.service;

import com.example.genie.civil.dto.PlanificationDTO;
import com.example.genie.civil.entity.*;
import com.example.genie.civil.mapper.PlanificationMapper;
import com.example.genie.civil.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanificationService {

    private final PlanificationRepository planificationRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ClientRepository clientRepository;
    private final VehiculeRepository vehiculeRepository;
    private final PlanificationMapper planificationMapper;

    public PlanificationService(PlanificationRepository planificationRepository,
                                UtilisateurRepository utilisateurRepository,
                                ClientRepository clientRepository,
                                VehiculeRepository vehiculeRepository,
                                PlanificationMapper planificationMapper) {
        this.planificationRepository = planificationRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.clientRepository = clientRepository;
        this.vehiculeRepository = vehiculeRepository;
        this.planificationMapper = planificationMapper;
    }

    public PlanificationDTO create(PlanificationDTO dto) {

        Planification planification = planificationMapper.toEntity(dto);

        Utilisateur utilisateur = utilisateurRepository.findById(dto.getIdUtilisateur())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        Client client = clientRepository.findById(dto.getIdClient())
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        Vehicule vehicule = vehiculeRepository.findById(dto.getIdVehicule())
                .orElseThrow(() -> new RuntimeException("Vehicule introuvable"));

        Utilisateur creePar = utilisateurRepository.findById(dto.getCreeParId())
                .orElseThrow(() -> new RuntimeException("Créateur introuvable"));

        planification.setUtilisateur(utilisateur);
        planification.setClient(client);
        planification.setVehicule(vehicule);
        planification.setCreePar(creePar);

        Planification saved = planificationRepository.save(planification);

        return planificationMapper.toDTO(saved);
    }

    public List<PlanificationDTO> findAll() {
        return planificationMapper.toDTOList(planificationRepository.findAll());
    }
}