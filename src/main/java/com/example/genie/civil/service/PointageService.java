package com.example.genie.civil.service;

import com.example.genie.civil.dto.PointageDTO;
import com.example.genie.civil.entity.Client;
import com.example.genie.civil.entity.Pointage;
import com.example.genie.civil.entity.Utilisateur;
import com.example.genie.civil.mapper.EntityMapper;
import com.example.genie.civil.repository.PointageRepository;

import com.example.genie.civil.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PointageService {

    private final PointageRepository pointageRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final EntityMapper entityMapper;

    @Transactional(readOnly = true)
    public List<PointageDTO> findAll() {
        return pointageRepository.findAll()
                .stream()
                .map(entityMapper::pointageToDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public PointageDTO findById(Long id) {
        return pointageRepository.findById(id)
                .map(entityMapper::pointageToDto)
                .orElseThrow(() ->
                        new RuntimeException("Pointage non trouvé avec l'ID: " + id));
    }

    public PointageDTO save(PointageDTO dto) {
        Pointage pointage = entityMapper.pointageToEntity(dto);

        // Set Utilisateur if provided
        if (dto.getIdUtilisateur() != null) {
            Utilisateur utilisateur = utilisateurRepository.findById(dto.getIdUtilisateur())
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé: " + dto.getIdUtilisateur()));
            pointage.setUtilisateur(utilisateur);
        }

        Pointage saved = pointageRepository.save(pointage);
        return entityMapper.pointageToDto(saved);
    }

    public void delete(Long id) {
        if (!pointageRepository.existsById(id)) {
            throw new RuntimeException("Pointage non trouvé avec l'ID: " + id);
        }
        pointageRepository.deleteById(id);
    }
}