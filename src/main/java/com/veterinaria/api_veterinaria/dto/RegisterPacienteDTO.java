package com.veterinaria.api_veterinaria.dto;

import java.time.LocalDate;

public record RegisterPacienteDTO(String nombreD, String apellidoPD, String apellidoMD, String telefono, String email,
String nombreM, Integer edad, String raza, String especie, String sexo, LocalDate fechaNacimiento) {

}
