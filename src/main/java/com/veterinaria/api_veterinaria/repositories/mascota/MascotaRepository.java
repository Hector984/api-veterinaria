package com.veterinaria.api_veterinaria.repositories.mascota;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.veterinaria.api_veterinaria.dto.RegisterPacienteDTO;
import com.veterinaria.api_veterinaria.entities.mascota.Dueno;
import com.veterinaria.api_veterinaria.entities.mascota.Mascota;

@Repository
public interface MascotaRepository extends CrudRepository<Mascota, Long> {

    public void registrarMascota(RegisterPacienteDTO datosPaciente, Dueno dueno);
}
