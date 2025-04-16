package com.veterinaria.api_veterinaria.repositories.negocio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.veterinaria.api_veterinaria.entities.negocio.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{

    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String username);
}
