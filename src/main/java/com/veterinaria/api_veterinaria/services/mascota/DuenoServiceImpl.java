package com.veterinaria.api_veterinaria.services.mascota;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.veterinaria.api_veterinaria.dto.RegisterPacienteDTO;
import com.veterinaria.api_veterinaria.entities.mascota.Dueno;
import com.veterinaria.api_veterinaria.repositories.mascota.DuenoRepository;

import jakarta.transaction.Transactional;

@Service
public class DuenoServiceImpl implements DuenoService {
    // Implementación de los métodos de la interfaz PacienteService
    // Aquí puedes agregar la lógica necesaria para manejar los pacientes
    private DuenoRepository duenoRepository;

    public DuenoServiceImpl(DuenoRepository duenoRepository) {
        this.duenoRepository = duenoRepository;
    }

    public Optional<Dueno> findByEmail(String email) {
        return duenoRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public Dueno save(Dueno dueno) {
        return duenoRepository.save(dueno);
    }

    @Transactional
    public Dueno registrarDuenoMascota(RegisterPacienteDTO paciente)
    {
        Dueno dueno = new Dueno();
        dueno.setNombre(paciente.nombreD());
        dueno.setApellidoP(paciente.apellidoPD());
        dueno.setApellidoM(paciente.apellidoMD());
        dueno.setTelefono(paciente.telefono());
        dueno.setEmail(paciente.email());

        return save(dueno);
    }

}
