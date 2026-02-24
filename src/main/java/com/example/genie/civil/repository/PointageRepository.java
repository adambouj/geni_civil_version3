package com.example.genie.civil.repository;
import com.example.genie.civil.entity.Pointage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PointageRepository extends JpaRepository<Pointage, Long> {}
