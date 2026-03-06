package com.example.genie.civil.repository;
import com.example.genie.civil.entity.Fichier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FichierRepository extends JpaRepository<Fichier, Long> {

    List<Fichier> findByMission_IdMission(Long idMission);
}
