package com.example.genie.civil.controller;

import com.example.genie.civil.dto.MaterielDTO;
import com.example.genie.civil.service.MaterielService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materiels")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MaterielController {

    private final MaterielService materielService;

    @GetMapping
    public ResponseEntity<List<MaterielDTO>> getAll() {
        return ResponseEntity.ok(materielService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterielDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(materielService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MaterielDTO> create(@Valid @RequestBody MaterielDTO dto) {
        MaterielDTO created = materielService.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterielDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody MaterielDTO dto) {

        return ResponseEntity.ok(materielService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        materielService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
