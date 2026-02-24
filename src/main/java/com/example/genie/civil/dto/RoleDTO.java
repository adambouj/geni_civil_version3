package com.example.genie.civil.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RoleDTO {
    private Long idRole;
    private String nomRole;
    private String description;
}
