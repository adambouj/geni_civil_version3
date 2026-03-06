package com.example.genie.civil.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FichierDTO {

    private Long idFichier;
    private String typeFichier;
    private String url;
    private Long missionId;
    private LocalDateTime dateUpload;
}