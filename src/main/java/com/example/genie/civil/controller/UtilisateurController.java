package com.example.genie.civil.controller;

import com.example.genie.civil.dto.UtilisateurDTO;
import com.example.genie.civil.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {
    private final UtilisateurService utilisateurService;

    @GetMapping
    public List<UtilisateurDTO> getAll() {
        return utilisateurService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(utilisateurService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<UtilisateurDTO> getByEmail(@RequestParam String email) {
        return ResponseEntity.ok(utilisateurService.findByEmail(email));
    }

    @PostMapping
    public ResponseEntity<UtilisateurDTO> create(@RequestBody UtilisateurDTO dto) {
        return ResponseEntity.ok(utilisateurService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> update(@PathVariable Long id, @RequestBody UtilisateurDTO dto) {
        dto.setIdUtilisateur(id);
        return ResponseEntity.ok(utilisateurService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        utilisateurService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
