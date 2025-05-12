package com.veterinaria.api_veterinaria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinaria.api_veterinaria.dto.RegisterResponseDTO;
import com.veterinaria.api_veterinaria.requests.auth.RegisterRequest;
import com.veterinaria.api_veterinaria.services.RolService;
import com.veterinaria.api_veterinaria.services.UsuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Registro", description = "Operaciones relacionadas con el registro usuarios con rol veterinario")
@RestController
@RequestMapping("/api/v1/registro")
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("")
    public ResponseEntity<RegisterResponseDTO> registro(@RequestBody final RegisterRequest request) {

        final RegisterResponseDTO registerResponseDTO = usuarioService.register(request);

        return ResponseEntity.ok(registerResponseDTO);
    }
}
