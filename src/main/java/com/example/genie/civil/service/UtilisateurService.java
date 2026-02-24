package com.example.genie.civil.service;

import com.example.genie.civil.dto.UtilisateurDTO;
import com.example.genie.civil.entity.Role;
import com.example.genie.civil.entity.Utilisateur;
import com.example.genie.civil.mapper.EntityMapper;
import com.example.genie.civil.repository.RoleRepository;
import com.example.genie.civil.repository.UtilisateurRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private final EntityMapper entityMapper;

    public List<UtilisateurDTO> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(entityMapper::utilisateurToDto)
                .collect(Collectors.toList());
    }

    public UtilisateurDTO findById(Long id) {
        Utilisateur u = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé: " + id));
        return entityMapper.utilisateurToDto(u);
    }
    @Transactional(readOnly = true)
    public UtilisateurDTO findByEmail(String email) {
        return utilisateurRepository.findByEmail(email)
                .map(entityMapper::utilisateurToDto)
                .orElseThrow(() ->
                        new RuntimeException("Utilisateur non trouvé avec l'email: " + email));
    }

    public UtilisateurDTO save(UtilisateurDTO dto) {
        // Convert DTO to entity (roles ignored for now)
        Utilisateur utilisateur = entityMapper.utilisateurToEntity(dto);

        // Make sure roles Set is not null
        if (utilisateur.getRoles() == null) {
            utilisateur.setRoles(new HashSet<>());
        }

        // Assign roles from roleIds
        if (dto.getRoleIds() != null && !dto.getRoleIds().isEmpty()) {
            Set<Role> roles = dto.getRoleIds().stream()
                    .map(id -> roleRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Role non trouvé: " + id)))
                    .collect(Collectors.toSet());
            utilisateur.setRoles(roles); // <-- MUST be before save
        }

        // Save utilisateur along with roles
        Utilisateur saved = utilisateurRepository.save(utilisateur);

        // Convert back to DTO (roleIds will now be populated)
        return entityMapper.utilisateurToDto(saved);
    }

    public void delete(Long id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new RuntimeException("Utilisateur non trouvé: " + id);
        }
        utilisateurRepository.deleteById(id);
    }
}