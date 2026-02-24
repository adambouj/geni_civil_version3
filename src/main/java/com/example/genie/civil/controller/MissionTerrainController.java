package com.example.genie.civil.controller;

import com.example.genie.civil.dto.MissionTerrainDTO;
import com.example.genie.civil.service.MissionTerrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionTerrainController {
    private final MissionTerrainService missionTerrainService;

    @GetMapping
    public List<MissionTerrainDTO> getAll() {
        return missionTerrainService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MissionTerrainDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(missionTerrainService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MissionTerrainDTO> create(@RequestBody MissionTerrainDTO dto) {
        return ResponseEntity.ok(missionTerrainService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MissionTerrainDTO> update(@PathVariable Long id, @RequestBody MissionTerrainDTO dto) {
        dto.setIdMission(id);
        return ResponseEntity.ok(missionTerrainService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        missionTerrainService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
