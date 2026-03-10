package com.example.genie.civil.controller;

import com.example.genie.civil.dto.FichierDTO;
import com.example.genie.civil.service.FichierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fichiers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FichierController {

    private final FichierService fichierService;

    // CREATE
    @PostMapping
    public ResponseEntity<FichierDTO> create(
            @Valid @RequestBody FichierDTO dto) {
        return ResponseEntity.ok(fichierService.create(dto));
    }

    // READ ALL by mission
    @GetMapping("/mission/{missionId}")
    public ResponseEntity<List<FichierDTO>> getByMission(
            @PathVariable Long missionId) {
        return ResponseEntity.ok(fichierService.findByMission(missionId));
    }

    // READ single by ID
    @GetMapping("/{id}")
    public ResponseEntity<FichierDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(fichierService.findById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<FichierDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody FichierDTO dto) {
        return ResponseEntity.ok(fichierService.update(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        fichierService.delete(id);
        return ResponseEntity.noContent().build();
    }
}