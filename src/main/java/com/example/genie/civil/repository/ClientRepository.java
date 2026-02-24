package com.example.genie.civil.repository;
import com.example.genie.civil.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    java.util.Optional<Client> findByNomClient(String nomClient);
}
