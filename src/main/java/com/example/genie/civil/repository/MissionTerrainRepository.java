package com.example.genie.civil.repository;
import com.example.genie.civil.entity.MissionTerrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MissionTerrainRepository extends JpaRepository<MissionTerrain, Long> {

    Optional<MissionTerrain> findByNumeroIntervention(String numeroIntervention);
//✔ Numéro intervention est souvent unique.

    List<MissionTerrain> findByUtilisateur_IdUtilisateur(Long idUtilisateur);

    List<MissionTerrain> findByClient_IdClient(Long idClient);
}
