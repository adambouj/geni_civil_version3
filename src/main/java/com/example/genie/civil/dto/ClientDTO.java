package com.example.genie.civil.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ClientDTO {
    private Long idClient;
    private String nomClient;
    private String siret;
    private String adresse;
    private String telephone;
    private String email;
    private LocalDateTime dateCreation;
}
