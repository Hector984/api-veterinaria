package com.veterinaria.api_veterinaria.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.veterinaria.api_veterinaria.dto.RegisterResponseDTO;
import com.veterinaria.api_veterinaria.entities.negocio.Rol;
import com.veterinaria.api_veterinaria.entities.negocio.Usuario;
import com.veterinaria.api_veterinaria.repositories.negocio.RolRepository;
import com.veterinaria.api_veterinaria.repositories.negocio.UsuarioRepository;
import com.veterinaria.api_veterinaria.requests.auth.RegisterRequest;
import com.veterinaria.api_veterinaria.services.auth.JwtService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Transactional
    public RegisterResponseDTO register(RegisterRequest request) {

        // Transformar el register request a un usuario
        Usuario usuario = Usuario.builder()
                .nombre(request.nombre())
                .apellidoP(request.apellidoP())
                .apellidoM(request.apellidoM())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();

        // Asignamos el rol veterinario por defecto
        Set<Rol> roles = new HashSet<>();
        Optional<Rol> optionalRol = rolRepository.findByNombre("VETERINARIO");
        optionalRol.ifPresent(rol -> roles.add(rol));
        // optionalRol.ifPresent(roles::add);
        usuario.setRoles(roles);

        if (request.dueno()) {
            optionalRol = rolRepository.findByNombre("DUENO_MASCOTA");
            optionalRol.ifPresent(rol -> roles.add(rol));
            // optionalRol.ifPresent(roles::add);
            usuario.setRoles(roles);
        }

        // Guardamos al usuario
        var nuevoUsuario = usuarioRepository.save(usuario);

        // Generamos el token
        var jwtToken = jwtService.generateToken(nuevoUsuario);

        return new RegisterResponseDTO(request.email(), jwtToken);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
}
