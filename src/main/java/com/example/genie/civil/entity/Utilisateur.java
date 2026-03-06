package com.example.genie.civil.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "utilisateurs")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateur;

    private String nom;
    private String prenom;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    private String motDePasse;
    private Boolean actif;
    private LocalDateTime dateCreation;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "utilisateur_role",
            joinColumns = @JoinColumn(name = "id_utilisateur"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private Set<Role> roles = new HashSet<>();
    // RELATIONS

    @OneToMany(mappedBy = "utilisateur")
    private Set<Planification> planifications;

    @OneToMany(mappedBy = "utilisateur")
    private Set<MissionTerrain> missions;

    @OneToMany(mappedBy = "utilisateur")
    private Set<Pointage> pointages;

    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDateTime.now();
        if (actif == null) actif = true;
    }
}
