package com.veterinaria.api_veterinaria.filter;

import static com.veterinaria.api_veterinaria.security.TokenJwtConfig.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.veterinaria.api_veterinaria.security.SimpleGrantedAuthorityJsonCreator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidationFilter extends BasicAuthenticationFilter {

    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        // Aqui vamos a recuperar el token que enviamos al usuario en el login o en el registro
        String header = request.getHeader(HEADER_AUTHORIZATION);

        // Verificamos que el header contenga el prefijo Bearer y que no este vacio
        // Si esta vacio significa que no ha iniciado sesion o no tiene un token
        if(header == null || !header.startsWith(PREFIX_TOKEN)) {
            chain.doFilter(request, response);
            return;
        }

        // Recuperamos solamente el token, elimnando la palabra Bearer
        // Recordar que el Header Authentication es igual a Bearer token
        String token = header.replace(PREFIX_TOKEN, "");
        System.out.println("Token: " + token);
        System.out.println("Header: " + header);
        

        try {

            // Validamos el token
            Claims claims = Jwts.parser()
            .verifyWith(SECRET_KEY)
            .build()
            .parseSignedClaims(token)
            .getPayload();

            String username = claims.getSubject();
            // String username2 = (String) claims.get("username");
            // Aqui los roles vivnen como un string en formato json
            Object authoritiesClaims = claims.get("authorities");

            System.out.println("Claims: " + claims);
            System.out.println("Usuario: " + username);
            

            // Convertimos los roles a una coleccion del tipo GrantedAuthority
            Collection<? extends GrantedAuthority> authorities = Arrays.asList(new ObjectMapper()
            .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
            .readValue(authoritiesClaims.toString().getBytes(), SimpleGrantedAuthority[].class));

            System.out.println("Authorities: " + authorities);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            
            chain.doFilter(request, response);

        } catch (Exception e) {
            Map<String, String> body = new HashMap<>();
            body.put("error", e.getMessage());

            body.put("message", "El token JWT es invalido!");

            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(CONTENT_TYPE);
        }

    }

}
