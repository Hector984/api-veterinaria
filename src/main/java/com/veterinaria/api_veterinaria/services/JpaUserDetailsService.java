package com.veterinaria.api_veterinaria.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.veterinaria.api_veterinaria.entities.negocio.Usuario;
import com.veterinaria.api_veterinaria.repositories.negocio.UsuarioRepository;

@Service

// Una clase que implementa la interfaz UserDetailsService se utiliza para cargar los detalles del usuario desde una 
// fuente de datos (como una base de datos) y crear un objeto UserDetails que representa al usuario autenticado.
// Esta clase es parte del proceso de autenticación y autorización en Spring Security.
// Spring lo detecta automaticamente al cargarse la aplicación, ya que es un Bean
public class JpaUserDetailsService implements UserDetailsService{

    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<Usuario> usuario = usuarioRepository.findByEmail(username);

        if(usuario.isEmpty()) {
            throw new UsernameNotFoundException(String.format("El usuario con correo %s , no existe en el sistema!", username));
        }

        Usuario user = usuario.orElseThrow();

        List<GrantedAuthority> authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getNombre()))
        .collect(Collectors.toList());

        return new User(user.getEmail(), 
        user.getPassword(), 
        user.isActivo(), 
        true, 
        true, 
        true, 
        authorities);

        // User.builder()
        //         .username(user.getEmail())
        //         .password(user.getPassword())
        //         .disabled(!user.isActivo())
        //         .accountExpired(!user.isAccountNonExpired())
        //         .accountLocked(!user.isAccountNonLocked())
        //         .credentialsExpired(!user.isCredentialsNonExpired())
        //         .authorities(authorities)
        //         .build();
    }

}
