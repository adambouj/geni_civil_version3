package com.example.genie.civil.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UtilisateurCreateDTO {

    @NotBlank(message = "remplir nom ")
    private String nom;

    @NotBlank(message = "remplir  prénom")
    private String prenom;

    @NotBlank(message = "email obligatoire")
    @Email(message = "email non valide")
    private String email;


    @NotBlank(message = "mot de passe obligatoire")

    private String motDePasse;

    private Set<Long> roleIds;
}