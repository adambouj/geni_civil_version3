package com.example.genie.civil.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtilisateurResponseDTO {

    private Long idUtilisateur;
    private String nom;
    private String prenom;
    private String email;
    private Boolean actif;
    private LocalDateTime dateCreation;
    private Set<RoleDTO> roles;
}