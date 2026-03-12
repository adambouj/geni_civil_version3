package com.example.genie.civil.controller;

import com.example.genie.civil.dto.PointageDTO;
import com.example.genie.civil.service.PointageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/pointages")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PointageController {

    private final PointageService pointageService;

    @GetMapping
    public ResponseEntity<List<PointageDTO>> getAll() {
        return ResponseEntity.ok(pointageService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PointageDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(pointageService.findById(id));
    }

    @GetMapping("/utilisateur/{id}")
    public ResponseEntity<List<PointageDTO>> getByUtilisateur(@PathVariable Long id) {
        return ResponseEntity.ok(pointageService.findByUtilisateur(id));
    }

    @GetMapping("/daterange")
    public ResponseEntity<List<PointageDTO>> getByDateRange(
            @RequestParam("start") String start,
            @RequestParam("end") String end) {

        LocalDateTime startDate = LocalDateTime.parse(start);
        LocalDateTime endDate = LocalDateTime.parse(end);

        return ResponseEntity.ok(pointageService.findByDateRange(startDate, endDate));
    }

    @GetMapping("/utilisateur/{id}/daterange")
    public ResponseEntity<List<PointageDTO>> getByUtilisateurAndDateRange(
            @PathVariable Long id,
            @RequestParam("start") String start,
            @RequestParam("end") String end) {

        LocalDateTime startDate = LocalDateTime.parse(start);
        LocalDateTime endDate = LocalDateTime.parse(end);

        return ResponseEntity.ok(pointageService.findByUtilisateurAndDateRange(id, startDate, endDate));
    }

    @PostMapping
    public ResponseEntity<PointageDTO> create(@Valid @RequestBody PointageDTO dto) {
        PointageDTO created = pointageService.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PointageDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody PointageDTO dto) {
        return ResponseEntity.ok(pointageService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pointageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}