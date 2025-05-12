package com.veterinaria.api_veterinaria.services.mascota;

import org.springframework.stereotype.Service;

import com.veterinaria.api_veterinaria.entities.mascota.Mascota;
import com.veterinaria.api_veterinaria.repositories.mascota.MascotaRepository;

import jakarta.transaction.Transactional;

@Service
public class MascotaServiceImpl implements MascotaService {

    private MascotaRepository repository;

    public MascotaServiceImpl(MascotaRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Mascota save(Mascota mascota) {

        return repository.save(mascota);
    }
}
