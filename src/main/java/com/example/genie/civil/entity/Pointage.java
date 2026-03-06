package com.example.genie.civil.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pointages")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pointage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPointage;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = true)
    private Client client;

    private LocalDateTime dateHeureDebut;
    private LocalDateTime dateHeureFin;
    private String description;
    private Integer dureeMinutes;
    private LocalDateTime dateSaisie;

    @PrePersist
    protected void onCreate() {
        dateSaisie = LocalDateTime.now();
    }
}