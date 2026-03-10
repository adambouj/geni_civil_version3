package com.example.genie.civil.service;

import com.example.genie.civil.dto.PointageDTO;
import com.example.genie.civil.entity.Pointage;
import com.example.genie.civil.entity.Utilisateur;
import com.example.genie.civil.mapper.PointageMapper;
import com.example.genie.civil.repository.PointageRepository;
import com.example.genie.civil.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PointageService {

    private final PointageRepository pointageRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final PointageMapper pointageMapper;

    public PointageService(PointageRepository pointageRepository,
                           UtilisateurRepository utilisateurRepository,
                           PointageMapper pointageMapper) {
        this.pointageRepository = pointageRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.pointageMapper = pointageMapper;
    }

    public PointageDTO create(PointageDTO dto) {
        Pointage pointage = pointageMapper.toEntity(dto);

        Utilisateur utilisateur = utilisateurRepository.findById(dto.getIdUtilisateur())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        pointage.setUtilisateur(utilisateur);

        // Calcul automatique durée
        if (dto.getDateHeureDebut() != null && dto.getDateHeureFin() != null) {
            long minutes = Duration.between(dto.getDateHeureDebut(), dto.getDateHeureFin()).toMinutes();
            pointage.setDureeMinutes((int) minutes);
        }

        Pointage saved = pointageRepository.save(pointage);
        return pointageMapper.toDTO(saved);
    }

    public PointageDTO findById(Long id) {
        Pointage pointage = pointageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pointage non trouvé"));
        return pointageMapper.toDTO(pointage);
    }

    public List<PointageDTO> findAll() {
        return pointageMapper.toDTOList(pointageRepository.findAll());
    }

    public List<PointageDTO> findByUtilisateur(Long idUtilisateur) {
        return pointageMapper.toDTOList(pointageRepository.findByUtilisateur_IdUtilisateur(idUtilisateur));
    }

    public List<PointageDTO> findByDateRange(LocalDateTime start, LocalDateTime end) {
        return pointageMapper.toDTOList(pointageRepository.findByDateHeureDebutBetween(start, end));
    }

    public List<PointageDTO> findByUtilisateurAndDateRange(Long idUtilisateur, LocalDateTime start, LocalDateTime end) {
        return pointageMapper.toDTOList(pointageRepository.findByUtilisateur_IdUtilisateurAndDateHeureDebutBetween(idUtilisateur, start, end));
    }

    public PointageDTO update(Long id, PointageDTO dto) {
        Pointage pointage = pointageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pointage non trouvé"));

        Utilisateur utilisateur = utilisateurRepository.findById(dto.getIdUtilisateur())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        pointage.setUtilisateur(utilisateur);

        pointage.setDateHeureDebut(dto.getDateHeureDebut());
        pointage.setDateHeureFin(dto.getDateHeureFin());
        pointage.setDescription(dto.getDescription());

        if (dto.getDateHeureDebut() != null && dto.getDateHeureFin() != null) {
            long minutes = Duration.between(dto.getDateHeureDebut(), dto.getDateHeureFin()).toMinutes();
            pointage.setDureeMinutes((int) minutes);
        }

        Pointage updated = pointageRepository.save(pointage);
        return pointageMapper.toDTO(updated);
    }

    public void delete(Long id) {
        Pointage pointage = pointageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pointage non trouvé"));
        pointageRepository.delete(pointage);
    }
}