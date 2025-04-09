package com.veterinaria.api_veterinaria.services;

import java.util.Optional;

import com.veterinaria.api_veterinaria.entities.negocio.Rol;

public interface RolService {

    public Optional<Rol> findByNombre(String nombre);

    public Rol save(Rol rol);
}
