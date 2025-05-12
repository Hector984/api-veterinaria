package com.veterinaria.api_veterinaria.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinaria.api_veterinaria.entities.negocio.Usuario;
import com.veterinaria.api_veterinaria.services.UsuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios del sistema")
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuariosController {

    private UsuarioService usuarioService;

    public UsuariosController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("")
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }
}
