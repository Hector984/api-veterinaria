package com.veterinaria.api_veterinaria.services;

import org.springframework.stereotype.Service;

import com.veterinaria.api_veterinaria.entities.negocio.Rol;
import com.veterinaria.api_veterinaria.repositories.negocio.RolRepository;

@Service
public class RolService {

    private RolRepository rolRepository;

    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }
}
