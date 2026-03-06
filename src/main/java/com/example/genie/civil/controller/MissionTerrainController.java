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

    @PostMapping
    public ResponseEntity<MissionTerrainDTO> create(
            @Valid @RequestBody MissionTerrainDTO dto) {
        return ResponseEntity.ok(missionService.create(dto));
    }
}