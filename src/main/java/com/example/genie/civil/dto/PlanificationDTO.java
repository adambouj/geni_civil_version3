package com.example.genie.civil.dto;

import com.example.genie.civil.entity.Utilisateur;
import com.example.genie.civil.entity.Vehicule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanificationDTO {
    private Long idPlanification;
    private Long idUtilisateur;
    private Long idClient;
    private Long idVehicule;
    private String description;
    private String statut;
    private LocalDateTime datePlanifiee;
    private String creePar;
}
