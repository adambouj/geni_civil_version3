package com.example.genie.civil.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "clients")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    private String nomClient;
    private String siret;
    private String adresse;
    private String telephone;
    private String email;
    private LocalDateTime dateCreation;

    @OneToMany(mappedBy = "client")
    private Set<Planification> planifications;

    @OneToMany(mappedBy = "client")
    private Set<MissionTerrain> missions;



    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDateTime.now();
    }
}
