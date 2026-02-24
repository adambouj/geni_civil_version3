package com.example.genie.civil.controller;

import com.example.genie.civil.dto.PointageDTO;
import com.example.genie.civil.service.PointageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pointages")
@RequiredArgsConstructor
public class PointageController {
    private final PointageService pointageService;

    @GetMapping
    public List<PointageDTO> getAll() {
        return pointageService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PointageDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(pointageService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PointageDTO> create(@RequestBody PointageDTO dto) {
        return ResponseEntity.ok(pointageService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PointageDTO> update(@PathVariable Long id, @RequestBody PointageDTO dto) {
        dto.setIdPointage(id);
        return ResponseEntity.ok(pointageService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pointageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
