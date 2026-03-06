package com.example.genie.civil.repository;
import com.example.genie.civil.entity.Planification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PlanificationRepository extends JpaRepository<Planification, Long> {

    List<Planification> findByUtilisateur_IdUtilisateur(Long idUtilisateur);
//✔ Permet récupérer planifications par utilisateur.
List<Planification> findByDatePlanifiee(LocalDateTime datePlanifiee);

    List<Planification> findByClient_IdClient(Long idClient);

    List<Planification> findByDatePlanifieeBetween(LocalDateTime start, LocalDateTime end);


}
