package com.veterinaria.api_veterinaria.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinaria.api_veterinaria.dto.RegisterResponseDTO;
import com.veterinaria.api_veterinaria.entities.negocio.Rol;
import com.veterinaria.api_veterinaria.entities.negocio.Usuario;
import com.veterinaria.api_veterinaria.requests.auth.RegisterRequest;
import com.veterinaria.api_veterinaria.services.RolService;
import com.veterinaria.api_veterinaria.services.UsuarioService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/registro")
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @GetMapping("usuarios")
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }

    // @PostMapping("usuario")
    // public ResponseEntity<Usuario> save(@Valid @RequestBody Usuario usuario) {

    //     if(usuario.isVeterinario()) {
    //         Set<Rol> roles = new HashSet<>();
    //         Optional<Rol> optionalRol = rolService.findByNombre("VETERINARIO");
    //         optionalRol.ifPresent(rol -> roles.add(rol));
    //         // optionalRol.ifPresent(roles::add);
    //         usuario.setRoles(roles);
    //     }

    //     if(usuario.isDueno()) {
    //         Set<Rol> roles = new HashSet<>();
    //         Optional<Rol> optionalRol = rolService.findByNombre("DUENO_MASCOTA");
    //         optionalRol.ifPresent(rol -> roles.add(rol));
    //         // optionalRol.ifPresent(roles::add);
    //         usuario.setRoles(roles);
    //     }

    //     return ResponseEntity.ok(usuarioService.save(usuario));
    // }

    @PostMapping("usuario")
    public ResponseEntity<RegisterResponseDTO> registro(@RequestBody final RegisterRequest request) {

        final RegisterResponseDTO registerResponseDTO = usuarioService.register(request);

        return ResponseEntity.ok(registerResponseDTO);
    }
}
