package com.example.genie.civil.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "materiels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Materiel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMateriel;

    @Column(nullable = false)
    private String nom;

    @Column(unique = true)
    private String reference;

    private String etat;

    private LocalDateTime dateCreation;

    @PrePersist
    public void prePersist() {
        this.dateCreation = LocalDateTime.now();
    }

    //  matériel → plusieurs planifications
    @OneToMany(mappedBy = "materiel")
    private Set<Planification> planifications;
    //  matériel → plusieurs missions
    @OneToMany(mappedBy = "materiel")
    private Set<MissionTerrain> missions;
}