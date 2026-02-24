package com.example.genie.civil.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class VehiculeDTO {
    private Long idVehicule;
    private String numeroParc;
    private String immatriculation;
    private String etat;
    private LocalDateTime dateCreation;
}
