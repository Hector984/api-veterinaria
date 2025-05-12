package com.veterinaria.api_veterinaria.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.veterinaria.api_veterinaria.dto.RegisterPacienteDTO;
import com.veterinaria.api_veterinaria.entities.mascota.Dueno;
import com.veterinaria.api_veterinaria.entities.mascota.Mascota;
import com.veterinaria.api_veterinaria.enums.Sexo;
import com.veterinaria.api_veterinaria.repositories.mascota.DuenoRepository;
import com.veterinaria.api_veterinaria.repositories.mascota.MascotaRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController {

    private DuenoRepository duenoRepository;
    private MascotaRepository mascotaRepository;

    public PacienteController(DuenoRepository duenoRepository, MascotaRepository mascotaRepository) {
        this.duenoRepository = duenoRepository;
        this.mascotaRepository = mascotaRepository;
    }

    @PostMapping("")
    public ResponseEntity<?> postMethodName(@RequestBody RegisterPacienteDTO paciente) {

        // Creamos al dueño de la mascota
        Dueno dueno = new Dueno();
        dueno.setNombre(paciente.nombreD());
        dueno.setApellidoP(paciente.apellidoPD());
        dueno.setApellidoM(paciente.apellidoMD());
        dueno.setTelefono(paciente.telefono());
        dueno.setEmail(paciente.email());

        Dueno nuevoDueno = duenoRepository.save(dueno);

        Mascota nuevaMascota = new Mascota();
        nuevaMascota.setNombre(paciente.nombreM());
        nuevaMascota.setEdad(paciente.edad());
        nuevaMascota.setRaza(paciente.raza());
        nuevaMascota.setSexo(Sexo.valueOf(paciente.sexo().toUpperCase()));
        nuevaMascota.setFechaNacimiento(paciente.fechaNacimiento());
        nuevaMascota.setDueno(nuevoDueno);

        mascotaRepository.save(nuevaMascota);

        return ResponseEntity.ok("Paciente registrado correctamente");
    }
    
}
