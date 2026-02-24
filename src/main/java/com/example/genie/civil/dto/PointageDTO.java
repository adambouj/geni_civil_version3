package com.example.genie.civil.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PointageDTO {
    private Long idPointage;
    private Long idUtilisateur;
    private LocalDateTime dateHeureDebut;
    private LocalDateTime dateHeureFin;
    private String description;
    private Integer dureeMinutes;
    private LocalDateTime dateSaisie;
}
