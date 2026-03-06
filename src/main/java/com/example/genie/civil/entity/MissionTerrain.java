package com.example.genie.civil.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "missions_terrain")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class MissionTerrain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMission;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_vehicule")
    private Vehicule vehicule;

    @ManyToOne
    @JoinColumn(name = "id_materiel")
    private Materiel materiel;


    @Column(unique = true, nullable = false)
    private String numeroIntervention;
    private String typeIntervention;
    private LocalDateTime dateHeureDebut;
    private String statut;
    private LocalDateTime dateHeureFin;
    private Integer dureeTotale;

    @Column(length = 2000)

    private String description;
    private String observations;
    private LocalDateTime dateCreation;


    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private Set<Fichier> fichiers;


    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDateTime.now();
    }
}
