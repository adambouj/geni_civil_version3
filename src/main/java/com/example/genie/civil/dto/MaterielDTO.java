package com.example.genie.civil.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterielDTO {

    private Long idMateriel;
    @NotBlank(message = "Nom obligatoire")
    private String nom;

    private String reference;

    @NotBlank(message = "Etat obligatoire")
    private String etat;
    private LocalDateTime dateCreation;

}