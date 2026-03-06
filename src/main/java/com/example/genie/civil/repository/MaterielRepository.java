package com.example.genie.civil.repository;

import com.example.genie.civil.entity.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaterielRepository extends JpaRepository<Materiel, Long> {

    Optional<Materiel> findByReference(String reference);

    boolean existsByReference(String reference);
}