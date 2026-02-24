package com.example.genie.civil.repository;
import com.example.genie.civil.entity.Planification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PlanificationRepository extends JpaRepository<Planification, Long> {}
