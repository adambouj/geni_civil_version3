package com.example.genie.civil.controller;

import com.example.genie.civil.dto.VehiculeDTO;
import com.example.genie.civil.service.VehiculeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
public class VehiculeController {
    private final VehiculeService vehiculeService;

    @GetMapping
    public List<VehiculeDTO> getAll() {
        return vehiculeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculeDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(vehiculeService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<VehiculeDTO> getByImmatriculation(@RequestParam String immatriculation) {
        return ResponseEntity.ok(vehiculeService.findByImmatriculation(immatriculation));
    }

    @PostMapping
    public ResponseEntity<VehiculeDTO> create(@RequestBody VehiculeDTO dto) {
        return ResponseEntity.ok(vehiculeService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiculeDTO> update(@PathVariable Long id, @RequestBody VehiculeDTO dto) {
        dto.setIdVehicule(id);
        return ResponseEntity.ok(vehiculeService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vehiculeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
