package com.veterinaria.api_veterinaria.validators.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.veterinaria.api_veterinaria.repositories.negocio.UsuarioRepository;
import com.veterinaria.api_veterinaria.services.UsuarioService;

import jakarta.validation.ConstraintValidator;

@Component
public class EmailUnicoValidator implements ConstraintValidator<EmailUnico, String> {

    // private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    public EmailUnicoValidator(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public boolean isValid(String email, jakarta.validation.ConstraintValidatorContext context) {
        
        if(usuarioRepository == null) {
            return true; // Si el servicio no está disponible, no se puede validar

        }
        return !usuarioRepository.existsByEmail(email);
    }

}
