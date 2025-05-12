package com.veterinaria.api_veterinaria.services;

import java.util.List;

import com.veterinaria.api_veterinaria.dto.RegisterResponseDTO;
import com.veterinaria.api_veterinaria.entities.negocio.Usuario;
import com.veterinaria.api_veterinaria.requests.auth.RegisterRequest;

public interface UsuarioService {

    public List<Usuario> findAll();

    public RegisterResponseDTO register(RegisterRequest request);

    public boolean existsByEmail(String email);

}
