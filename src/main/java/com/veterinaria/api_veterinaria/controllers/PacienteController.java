package com.veterinaria.api_veterinaria.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.veterinaria.api_veterinaria.dto.RegisterPacienteDTO;
import com.veterinaria.api_veterinaria.entities.mascota.Dueno;
import com.veterinaria.api_veterinaria.entities.mascota.Mascota;
import com.veterinaria.api_veterinaria.enums.Sexo;
import com.veterinaria.api_veterinaria.repositories.mascota.DuenoRepository;
import com.veterinaria.api_veterinaria.repositories.mascota.MascotaRepository;
import com.veterinaria.api_veterinaria.services.mascota.DuenoService;
import com.veterinaria.api_veterinaria.services.mascota.MascotaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController {

    private DuenoService duenoService;
    private MascotaService mascotaService;

    public PacienteController(DuenoService duenoService, MascotaService mascotaService) {
        this.duenoService = duenoService;
        this.mascotaService = mascotaService;
    }

    @Operation(summary = "Registrar un nuevo paciente")
    @ApiResponse(responseCode = "200", description = "Paciente registrado correctamente")
    @PostMapping("")
    public ResponseEntity<?> registrarPaciente(@Valid @RequestBody RegisterPacienteDTO paciente) {

        // Creamos al dueño de la mascota
        Dueno dueno = duenoService.registrarDuenoMascota(paciente);

        // Registramos a la mascota
        mascotaService.registrarMascota(paciente, dueno);

        return ResponseEntity.ok("Paciente registrado correctamente");
    }
    
}
