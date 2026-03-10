package com.example.genie.civil.controller;

import com.example.genie.civil.dto.MissionTerrainDTO;
import com.example.genie.civil.service.MissionTerrainService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MissionTerrainController {

    private final MissionTerrainService missionService;

    @GetMapping
    public ResponseEntity<List<MissionTerrainDTO>> getAll() {
        return ResponseEntity.ok(missionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MissionTerrainDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(missionService.findById(id));
    }

    @GetMapping("/utilisateur/{id}")
    public ResponseEntity<List<MissionTerrainDTO>> getByUtilisateur(@PathVariable Long id) {
        return ResponseEntity.ok(missionService.findByUtilisateur(id));
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<MissionTerrainDTO>> getByClient(@PathVariable Long id) {
        return ResponseEntity.ok(missionService.findByClient(id));
    }

    @PostMapping
    public ResponseEntity<MissionTerrainDTO> create(
            @Valid @RequestBody MissionTerrainDTO dto) {

        MissionTerrainDTO created = missionService.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MissionTerrainDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody MissionTerrainDTO dto) {

        return ResponseEntity.ok(missionService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        missionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}