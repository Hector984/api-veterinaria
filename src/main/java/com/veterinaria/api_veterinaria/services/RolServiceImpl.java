package com.veterinaria.api_veterinaria.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.veterinaria.api_veterinaria.entities.negocio.Rol;
import com.veterinaria.api_veterinaria.repositories.negocio.RolRepository;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Rol> findByNombre(String nombre) {
        return (Optional<Rol>)rolRepository.findByNombre(nombre);

    }

    @Override
    @Transactional
    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }
}
