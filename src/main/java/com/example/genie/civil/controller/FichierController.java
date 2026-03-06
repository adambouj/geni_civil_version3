package com.example.genie.civil.controller;

import com.example.genie.civil.dto.FichierDTO;
import com.example.genie.civil.service.FichierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fichiers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FichierController {

    private final FichierService fichierService;

    @PostMapping
    public ResponseEntity<FichierDTO> create(
            @Valid @RequestBody FichierDTO dto) {
        return ResponseEntity.ok(fichierService.create(dto));
    }

    @GetMapping("/mission/{missionId}")
    public ResponseEntity<List<FichierDTO>> getByMission(
            @PathVariable Long missionId) {
        return ResponseEntity.ok(
                fichierService.findByMission(missionId)
        );
    }
}