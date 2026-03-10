package com.example.genie.civil.controller;

import com.example.genie.civil.dto.VehiculeDTO;
import com.example.genie.civil.service.VehiculeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VehiculeController {

    private final VehiculeService vehiculeService;

    @GetMapping
    public ResponseEntity<List<VehiculeDTO>> getAll() {
        return ResponseEntity.ok(vehiculeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculeDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(vehiculeService.findById(id));
    }

    @GetMapping("/immatriculation/{immatriculation}")
    public ResponseEntity<VehiculeDTO> getByImmatriculation(
            @PathVariable String immatriculation) {
        return ResponseEntity.ok(
                vehiculeService.findByImmatriculation(immatriculation)
        );
    }

    @PostMapping
    public ResponseEntity<VehiculeDTO> create(
            @Valid @RequestBody VehiculeDTO dto) {
        return ResponseEntity.ok(vehiculeService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiculeDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody VehiculeDTO dto) {
        return ResponseEntity.ok(vehiculeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vehiculeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}