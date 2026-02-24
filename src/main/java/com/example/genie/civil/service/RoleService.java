package com.example.genie.civil.service;

import com.example.genie.civil.dto.RoleDTO;
import com.example.genie.civil.entity.Role;
import com.example.genie.civil.mapper.EntityMapper;
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
    private final EntityMapper entityMapper;

    @Transactional(readOnly = true)
    public List<RoleDTO> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(entityMapper::roleToDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public RoleDTO findById(Long id) {
        return roleRepository.findById(id)
                .map(entityMapper::roleToDto)
                .orElseThrow(() ->
                        new RuntimeException("Role non trouvé avec l'ID: " + id));
    }

    @Transactional(readOnly = true)
    public RoleDTO findByName(String name) {
        return roleRepository.findByNomRole(name)
                .map(entityMapper::roleToDto)
                .orElseThrow(() ->
                        new RuntimeException("Role non trouvé avec le nom: " + name));
    }

    public RoleDTO save(RoleDTO dto) {
        Role entity = entityMapper.roleToEntity(dto);
        Role saved = roleRepository.save(entity);
        return entityMapper.roleToDto(saved);
    }

    public void delete(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Role non trouvé avec l'ID: " + id);
        }
        roleRepository.deleteById(id);
    }
}