package com.example.genie.civil.controller;

import com.example.genie.civil.dto.PlanificationDTO;
import com.example.genie.civil.service.PlanificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/planifications")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PlanificationController {

    private final PlanificationService planificationService;

    @GetMapping
    public ResponseEntity<List<PlanificationDTO>> getAll() {
        return ResponseEntity.ok(planificationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanificationDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(planificationService.findById(id));
    }

    @GetMapping("/utilisateur/{id}")
    public ResponseEntity<List<PlanificationDTO>> getByUtilisateur(@PathVariable Long id) {
        return ResponseEntity.ok(planificationService.findByUtilisateur(id));
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<PlanificationDTO>> getByClient(@PathVariable Long id) {
        return ResponseEntity.ok(planificationService.findByClient(id));
    }

    @GetMapping("/date")
    public ResponseEntity<List<PlanificationDTO>> getByDateRange(
            @RequestParam("start") String start,
            @RequestParam("end") String end) {

        LocalDateTime startDate = LocalDateTime.parse(start);
        LocalDateTime endDate = LocalDateTime.parse(end);

        return ResponseEntity.ok(planificationService.findByDatePlanifieeBetween(startDate, endDate));
    }

    @PostMapping
    public ResponseEntity<PlanificationDTO> create(
            @Valid @RequestBody PlanificationDTO dto) {

        PlanificationDTO created = planificationService.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanificationDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody PlanificationDTO dto) {

        return ResponseEntity.ok(planificationService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        planificationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}