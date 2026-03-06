package com.example.genie.civil.repository;
import com.example.genie.civil.entity.Pointage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PointageRepository extends JpaRepository<Pointage, Long> {
    List<Pointage> findByUtilisateur_IdUtilisateur(Long idUtilisateur);

    List<Pointage> findByDateHeureDebutBetween(LocalDateTime start, LocalDateTime end);

    List<Pointage> findByUtilisateur_IdUtilisateurAndDateHeureDebutBetween(
            Long idUtilisateur,
            LocalDateTime start,
            LocalDateTime end
    );
//Ça sera crucial pour calcul heures.
}
