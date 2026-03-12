package com.example.genie.civil.controller;

import com.example.genie.civil.dto.RoleDTO;
import com.example.genie.civil.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RoleController {

    private final RoleService roleService;

    // GET all roles
    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAll() {
        return ResponseEntity.ok(roleService.findAll());
    }

    // GET role by ID
    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.findById(id));
    }

    // GET role by name
    @GetMapping("/name/{name}")
    public ResponseEntity<RoleDTO> getByName(@PathVariable String name) {
        return ResponseEntity.ok(roleService.findByName(name));
    }

    // POST create role
    @PostMapping
    public ResponseEntity<RoleDTO> create(@Valid @RequestBody RoleDTO dto) {
        RoleDTO created = roleService.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    // PUT update role
    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody RoleDTO dto) {
        return ResponseEntity.ok(roleService.update(id, dto));
    }

    // DELETE role
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}