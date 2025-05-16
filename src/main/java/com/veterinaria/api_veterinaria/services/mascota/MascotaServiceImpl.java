package com.veterinaria.api_veterinaria.services.mascota;

import org.springframework.stereotype.Service;

import com.veterinaria.api_veterinaria.dto.RegisterPacienteDTO;
import com.veterinaria.api_veterinaria.entities.mascota.Dueno;
import com.veterinaria.api_veterinaria.entities.mascota.Mascota;
import com.veterinaria.api_veterinaria.enums.Sexo;
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

    @Transactional
    public void registrarMascota(RegisterPacienteDTO datosPaciente, Dueno dueno)
    {
        Mascota nuevaMascota = new Mascota();
        nuevaMascota.setNombre(datosPaciente.nombreM());
        nuevaMascota.setEdad(datosPaciente.edad());
        nuevaMascota.setRaza(datosPaciente.raza());
        nuevaMascota.setSexo(Sexo.valueOf(datosPaciente.sexo().toUpperCase()));
        nuevaMascota.setFechaNacimiento(datosPaciente.fechaNacimiento());
        nuevaMascota.setDueno(dueno);

        save(nuevaMascota);
    }
}
