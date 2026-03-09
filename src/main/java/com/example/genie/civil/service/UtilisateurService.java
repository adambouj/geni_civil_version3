package com.example.genie.civil.service;

import com.example.genie.civil.dto.UtilisateurCreateDTO;
import com.example.genie.civil.dto.UtilisateurResponseDTO;
import com.example.genie.civil.entity.Role;
import com.example.genie.civil.entity.Utilisateur;
import com.example.genie.civil.mapper.UtilisateurMapper;
import com.example.genie.civil.repository.RoleRepository;
import com.example.genie.civil.repository.UtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private final UtilisateurMapper utilisateurMapper;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurService(UtilisateurRepository utilisateurRepository,
                              RoleRepository roleRepository,
                              UtilisateurMapper utilisateurMapper,
                              PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
        this.utilisateurMapper = utilisateurMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // CREATE
    public UtilisateurResponseDTO create(UtilisateurCreateDTO dto) {

        if (utilisateurRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé");
        }

        Utilisateur utilisateur = utilisateurMapper.toEntity(dto);

        // Encrypt password
        utilisateur.setMotDePasse(passwordEncoder.encode(dto.getMotDePasse()));


        // Inject roles
        Set<Role> roles = new HashSet<>(roleRepository.findAllById(dto.getRoleIds()));
        utilisateur.setRoles(roles);

        Utilisateur saved = utilisateurRepository.save(utilisateur);

        return utilisateurMapper.toResponseDTO(saved);
    }

    // GET ALL
    public List<UtilisateurResponseDTO> findAll() {
        return utilisateurMapper.toResponseDTOList(utilisateurRepository.findAll());
    }

    // GET BY ID
    public UtilisateurResponseDTO findById(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        return utilisateurMapper.toResponseDTO(utilisateur);
    }

    // DELETE
    public void delete(Long id) {
        utilisateurRepository.deleteById(id);
    }
}