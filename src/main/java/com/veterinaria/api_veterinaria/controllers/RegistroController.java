package com.veterinaria.api_veterinaria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinaria.api_veterinaria.entities.negocio.Usuario;
import com.veterinaria.api_veterinaria.services.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/registro")
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("usuario")
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }

    @PostMapping("usuario")
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.save(usuario));
    }
}
