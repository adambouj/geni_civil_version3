package com.example.genie.civil.repository;
import com.example.genie.civil.entity.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
    java.util.Optional<Vehicule> findByImmatriculation(String immatriculation);
}
