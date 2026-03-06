package com.example.genie.civil.controller;

import com.example.genie.civil.dto.PointageDTO;
import com.example.genie.civil.service.PointageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pointages")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PointageController {

    private final PointageService pointageService;

    @GetMapping
    public ResponseEntity<List<PointageDTO>> getAll() {
        return ResponseEntity.ok(pointageService.findAll());
    }

    @PostMapping
    public ResponseEntity<PointageDTO> create(
            @Valid @RequestBody PointageDTO dto) {
        return ResponseEntity.ok(pointageService.create(dto));
    }
}