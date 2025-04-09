package com.veterinaria.api_veterinaria.repositories.negocio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.veterinaria.api_veterinaria.entities.negocio.Rol;

@Repository
public interface RolRepository extends CrudRepository<Rol, Integer> {

    public Optional<Rol> findByNombre(String nombre);
}
