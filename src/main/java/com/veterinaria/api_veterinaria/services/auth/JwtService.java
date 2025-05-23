package com.veterinaria.api_veterinaria.services.auth;

import static com.veterinaria.api_veterinaria.security.TokenJwtConfig.*;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.veterinaria.api_veterinaria.entities.negocio.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {

    public String generateToken(final Usuario usuario) {
        return buildToken(usuario, EXPIRATION_TIME);
    }

    private String buildToken(final Usuario usuario, final long expirationTime) {

        try {
            Collection<? extends GrantedAuthority> authorities = usuario.getRoles().stream()
                    .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.getNombre()))
                    .collect(Collectors.toList());

            Claims claims = Jwts.claims()
                    .add("authorities", new ObjectMapper().writeValueAsString(authorities))
                    .add("username", usuario.getEmail())
                    .build();

            return Jwts.builder()
                    .subject(usuario.getEmail())
                    .claims(claims)
                    .expiration(new Date(System.currentTimeMillis() + expirationTime))
                    .issuedAt(new Date())
                    .signWith(SECRET_KEY)
                    .compact();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error generando el token", e);
        }

    }
}
