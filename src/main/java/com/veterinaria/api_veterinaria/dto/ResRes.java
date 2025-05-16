package com.veterinaria.api_veterinaria.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResRes {

    private String nombre;

    private String apellidoP;

    private String apellidoM;

    private String email;

    private String password;

    private boolean activo;

    private ZonedDateTime fechaCreacion;

    private ZonedDateTime fechaModificacion;
}
