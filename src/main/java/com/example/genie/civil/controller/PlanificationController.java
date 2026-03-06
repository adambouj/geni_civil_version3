package com.example.genie.civil.controller;

import com.example.genie.civil.dto.PlanificationDTO;
import com.example.genie.civil.service.PlanificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<PlanificationDTO> create(
            @Valid @RequestBody PlanificationDTO dto) {
        return ResponseEntity.ok(planificationService.create(dto));
    }
}