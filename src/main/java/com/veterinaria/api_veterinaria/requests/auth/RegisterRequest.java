package com.veterinaria.api_veterinaria.requests.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Transient;

public record RegisterRequest (
    String nombre, 
    String apellidoP, 
    String apellidoM,
    String email,
    String password,
    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    boolean veterinario,
    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    boolean dueno) {

}
