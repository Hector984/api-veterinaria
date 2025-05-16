package com.veterinaria.api_veterinaria.services.mascota;

import java.util.Optional;

import com.veterinaria.api_veterinaria.dto.RegisterPacienteDTO;
import com.veterinaria.api_veterinaria.entities.mascota.Dueno;

public interface DuenoService {

    public Optional<Dueno> findByEmail(String email);

    public Dueno save(Dueno dueno);

    public Dueno registrarDuenoMascota(RegisterPacienteDTO paciente);
}
