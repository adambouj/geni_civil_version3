package com.example.genie.civil.service;

import com.example.genie.civil.dto.PlanificationDTO;
import com.example.genie.civil.entity.Client;
import com.example.genie.civil.entity.Planification;
import com.example.genie.civil.entity.Utilisateur;
import com.example.genie.civil.entity.Vehicule;
import com.example.genie.civil.mapper.EntityMapper;
import com.example.genie.civil.repository.ClientRepository;
import com.example.genie.civil.repository.PlanificationRepository;

import com.example.genie.civil.repository.UtilisateurRepository;
import com.example.genie.civil.repository.VehiculeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanificationService {

    private final PlanificationRepository planificationRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ClientRepository clientRepository;
    private final VehiculeRepository vehiculeRepository;
    private final EntityMapper entityMapper;

    @Transactional(readOnly = true)
    public List<PlanificationDTO> findAll() {
        return planificationRepository.findAll()
                .stream()
                .map(entityMapper::planificationToDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public PlanificationDTO findById(Long id) {
        return planificationRepository.findById(id)
                .map(entityMapper::planificationToDto)
                .orElseThrow(() ->
                        new RuntimeException("Planification non trouvée avec l'ID: " + id));
    }

    public PlanificationDTO save(PlanificationDTO dto) {
        Planification planification = entityMapper.planificationToEntity(dto);

        if (dto.getIdUtilisateur() != null) {
            Utilisateur utilisateur = utilisateurRepository.findById(dto.getIdUtilisateur())
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé: " + dto.getIdUtilisateur()));
            planification.setUtilisateur(utilisateur);
        }

        if (dto.getIdClient() != null) {
            Client client = clientRepository.findById(dto.getIdClient())
                    .orElseThrow(() -> new RuntimeException("Client non trouvé: " + dto.getIdClient()));
            planification.setClient(client);
        }

        if (dto.getIdVehicule() != null) {
            Vehicule vehicule = vehiculeRepository.findById(dto.getIdVehicule())
                    .orElseThrow(() -> new RuntimeException("Vehicule non trouvé: " + dto.getIdVehicule()));
            planification.setVehicule(vehicule);
        }

        Planification saved = planificationRepository.save(planification);
        return entityMapper.planificationToDto(saved);
    }

    public void delete(Long id) {
        if (!planificationRepository.existsById(id)) {
            throw new RuntimeException("Planification non trouvée avec l'ID: " + id);
        }
        planificationRepository.deleteById(id);
    }
}