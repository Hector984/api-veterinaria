package com.veterinaria.api_veterinaria.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record RegisterPacienteDTO(
    @NotBlank(message = "El nombre del dueño no puede estar vacío")
    String nombreD,
    @NotBlank(message = "El apellido paterno del dueño no puede estar vacío") 
    String apellidoPD,
    @NotBlank(message = "El apellido materno del dueño no puede estar vacío") 
    String apellidoMD,
    @NotBlank(message = "El teléfono no puede estar vacío") 
    String telefono,
    @NotBlank(message = "El correo no puede estar vacío") 
    String email,
    @NotBlank(message = "El nombre de la mascota no puede estar vacío")
    String nombreM,
    @NotNull(message = "La edad de la mascota no puede estar vacía")
    @Min(0) 
    Integer edad,
    @NotBlank(message = "La raza de la mascota no puede estar vacía")
    String raza,
    @NotBlank(message = "La especie de la mascota no puede estar vacía") 
    String especie,
    @NotBlank(message = "El sexo de la mascota no puede estar vacío") 
    String sexo,
    @NotNull(message = "La fecha de nacimiento de la mascota no puede estar vacía")
    @Past 
    LocalDate fechaNacimiento) {

}
