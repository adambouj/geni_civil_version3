package com.example.genie.civil.service;

import com.example.genie.civil.dto.RoleDTO;
import com.example.genie.civil.entity.Role;
import com.example.genie.civil.mapper.RoleMapper;
import com.example.genie.civil.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    // Get all roles
    @Transactional(readOnly = true)
    public List<RoleDTO> findAll() {
        return roleMapper.toDTOList(roleRepository.findAll());
    }

    // Get role by ID
    @Transactional(readOnly = true)
    public RoleDTO findById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role non trouvé avec l'ID: " + id));
        return roleMapper.toDTO(role);
    }

    // Get role by name
    @Transactional(readOnly = true)
    public RoleDTO findByName(String name) {
        Role role = roleRepository.findByNomRole(name)
                .orElseThrow(() -> new RuntimeException("Role non trouvé avec le nom: " + name));
        return roleMapper.toDTO(role);
    }

    // Create new role
    public RoleDTO create(RoleDTO dto) {
        // Optional: check if role with same name exists
        roleRepository.findByNomRole(dto.getNomRole()).ifPresent(r -> {
            throw new RuntimeException("Role avec ce nom existe déjà: " + dto.getNomRole());
        });

        Role role = roleMapper.toEntity(dto);
        Role saved = roleRepository.save(role);
        return roleMapper.toDTO(saved);
    }

    // Update existing role
    public RoleDTO update(Long id, RoleDTO dto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role non trouvé avec l'ID: " + id));

        role.setNomRole(dto.getNomRole());
        role.setDescription(dto.getDescription());

        Role updated = roleRepository.save(role);
        return roleMapper.toDTO(updated);
    }

    // Delete role
    public void delete(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role non trouvé avec l'ID: " + id));
        roleRepository.delete(role);
    }
}