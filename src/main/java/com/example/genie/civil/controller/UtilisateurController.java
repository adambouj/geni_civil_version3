package com.example.genie.civil.controller;

import com.example.genie.civil.dto.UtilisateurCreateDTO;
import com.example.genie.civil.dto.UtilisateurResponseDTO;
import com.example.genie.civil.service.UtilisateurService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    // CREATE
    @PostMapping
    public ResponseEntity<UtilisateurResponseDTO> create(
            @Valid @RequestBody UtilisateurCreateDTO dto) {
        return ResponseEntity.ok(utilisateurService.create(dto));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<UtilisateurResponseDTO>> getAll() {
        return ResponseEntity.ok(utilisateurService.findAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(utilisateurService.findById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody UtilisateurCreateDTO dto) {
        return ResponseEntity.ok(utilisateurService.update(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        utilisateurService.delete(id);
        return ResponseEntity.noContent().build();
    }
}