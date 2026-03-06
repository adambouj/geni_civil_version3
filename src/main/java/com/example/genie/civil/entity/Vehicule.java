package com.example.genie.civil.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "vehicules")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicule;

    @Column(unique = true)

    private String numeroParc;

    @Column(unique = true, nullable = false)

    private String immatriculation;
    private String etat;
    private LocalDateTime dateCreation;

    @OneToMany(mappedBy = "vehicule")
    private Set<Planification> planifications;

    @OneToMany(mappedBy = "vehicule")
    private Set<MissionTerrain> missions;

    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDateTime.now();
    }
}
