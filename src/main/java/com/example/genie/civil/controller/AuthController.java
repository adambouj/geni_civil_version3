package com.example.genie.civil.controller;

import com.example.genie.civil.dto.LoginRequestDTO;
import com.example.genie.civil.dto.LoginResponseDTO;
import com.example.genie.civil.service.AuthService;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {
        return authService.login(request);
    }
}