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

    @Transactional(readOnly = true)
    public List<RoleDTO> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public RoleDTO findById(Long id) {
        return roleRepository.findById(id)
                .map(roleMapper::toDTO)
                .orElseThrow(() ->
                        new RuntimeException("Role non trouvé avec l'ID: " + id));
    }

    @Transactional(readOnly = true)
    public RoleDTO findByName(String name) {
        return roleRepository.findByNomRole(name)
                .map(roleMapper::toDTO)
                .orElseThrow(() ->
                        new RuntimeException("Role non trouvé avec le nom: " + name));
    }

    public RoleDTO save(RoleDTO dto) {
        Role entity = roleMapper.toEntity(dto);
        Role saved = roleRepository.save(entity);
        return roleMapper.toDTO(saved);
    }

    public void delete(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Role non trouvé avec l'ID: " + id);
        }
        roleRepository.deleteById(id);
    }
}