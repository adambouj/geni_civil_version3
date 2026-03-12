package com.example.genie.civil.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MissionTerrainDTO {
    private Long idMission;
    private Long idUtilisateur;
    private String utilisateurNom;   // add this
    private String utilisateurPrenom; // add this
    private Long idClient;
    private String clientNom;         // add this
    private Long idVehicule;
    private String vehiculeImmatriculation; // optional
    private String numeroIntervention;
    private String typeIntervention;
    private LocalDateTime dateHeureDebut;
    private String statut;
    private LocalDateTime dateHeureFin;
    private Integer dureeTotale;
    private String description;
    private String observations;
}
