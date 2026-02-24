package com.example.genie.civil.repository;
import com.example.genie.civil.entity.MissionTerrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MissionTerrainRepository extends JpaRepository<MissionTerrain, Long> {}
