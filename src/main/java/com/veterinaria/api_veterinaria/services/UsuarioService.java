package com.veterinaria.api_veterinaria.services;

import java.util.List;

import com.veterinaria.api_veterinaria.entities.negocio.Usuario;

public interface UsuarioService {

    public List<Usuario> findAll();

    public Usuario save(Usuario usuario);
}
