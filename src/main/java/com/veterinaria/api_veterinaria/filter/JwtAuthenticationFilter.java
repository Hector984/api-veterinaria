package com.veterinaria.api_veterinaria.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    // Este filtro se encarga de interceptar las peticiones de autenticación y validar el token JWT
    // que se envía en la cabecera de la solicitud. Si el token es válido, permite el acceso a los recursos protegidos.
    // Si no es válido, devuelve un error de autenticación.
    // Aquí puedes implementar la lógica para validar el token JWT y establecer la autenticación en el contexto de seguridad.{

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authRequest);
    }

    @Override
    protected String obtainUserName(HttpServletRequest request) {
        return request.getParameter("email");
    }
}
