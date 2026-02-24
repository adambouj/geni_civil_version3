package com.example.genie.civil.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "fichiers")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Fichier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFichier;

    private String typeFichier;
    private String url;

    @ManyToOne
    @JoinColumn(name = "id_mission")
    private MissionTerrain mission;

    private LocalDateTime dateUpload;

    @PrePersist
    protected void onCreate() {
        dateUpload = LocalDateTime.now();
    }
}
