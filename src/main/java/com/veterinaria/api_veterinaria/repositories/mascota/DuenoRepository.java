package com.veterinaria.api_veterinaria.repositories.mascota;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.veterinaria.api_veterinaria.entities.mascota.Dueno;

@Repository
public interface DuenoRepository extends CrudRepository<Dueno, Long> {

    public Optional<Dueno> findByEmail(String email);
}
