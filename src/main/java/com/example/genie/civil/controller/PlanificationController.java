package com.example.genie.civil.controller;

import com.example.genie.civil.dto.PlanificationDTO;
import com.example.genie.civil.service.PlanificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planifications")
@RequiredArgsConstructor
public class PlanificationController {
    private final PlanificationService planificationService;

    @GetMapping
    public List<PlanificationDTO> getAll() {
        return planificationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanificationDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(planificationService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PlanificationDTO> create(@RequestBody PlanificationDTO dto) {
        return ResponseEntity.ok(planificationService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanificationDTO> update(@PathVariable Long id, @RequestBody PlanificationDTO dto) {
        dto.setIdPlanification(id);
        return ResponseEntity.ok(planificationService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        planificationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
