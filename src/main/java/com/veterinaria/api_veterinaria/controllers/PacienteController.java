package com.veterinaria.api_veterinaria.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.veterinaria.api_veterinaria.dto.RegisterPacienteDTO;
import com.veterinaria.api_veterinaria.entities.mascota.Dueno;
import com.veterinaria.api_veterinaria.repositories.mascota.DuenoRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController {

    private DuenoRepository duenoRepository;

    public PacienteController(DuenoRepository duenoRepository) {
        this.duenoRepository = duenoRepository;
    }

    @PostMapping("")
    public ResponseEntity<?> postMethodName(@RequestBody RegisterPacienteDTO paciente) {

        Dueno dueno = new Dueno();
        dueno.setNombre(paciente.nombreD());
        dueno.setApellidoP(paciente.apellidoPD());
        dueno.setApellidoM(paciente.apellidoMD());
        dueno.setTelefono(paciente.telefono());
        dueno.setEmail(paciente.email());

        duenoRepository.save(dueno);

        return ResponseEntity.ok("Paciente registrado correctamente");
    }
    
}
