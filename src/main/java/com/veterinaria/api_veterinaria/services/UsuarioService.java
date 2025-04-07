package com.veterinaria.api_veterinaria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veterinaria.api_veterinaria.entities.negocio.Usuario;
import com.veterinaria.api_veterinaria.repositories.negocio.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
