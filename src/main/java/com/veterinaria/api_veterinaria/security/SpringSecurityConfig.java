package com.veterinaria.api_veterinaria.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        return http.authorizeHttpRequests((authz) -> authz
            .requestMatchers("/api/v1/registro/**").permitAll() // Indica que permite a todoa acceder a esta ruta
            .anyRequest().authenticated()) // Para todas las demas peticiones se requiere autorizacion
            .csrf(config -> config.disable()) // Deshabilitamos la proteccion CSRF
            // Le indica a Spring que no debe usar sesiones Http para almacenar la autenticacion del usuario
            .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .build();
    }
}
