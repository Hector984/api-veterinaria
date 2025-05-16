package com.veterinaria.api_veterinaria.services.mascota;

import com.veterinaria.api_veterinaria.dto.RegisterPacienteDTO;
import com.veterinaria.api_veterinaria.entities.mascota.Dueno;
import com.veterinaria.api_veterinaria.entities.mascota.Mascota;

public interface MascotaService {

    public Mascota save(Mascota mascota);  

    public void registrarMascota(RegisterPacienteDTO datosPaciente, Dueno dueno);
}
