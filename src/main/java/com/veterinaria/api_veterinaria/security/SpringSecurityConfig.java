package com.veterinaria.api_veterinaria.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.veterinaria.api_veterinaria.filter.JwtAuthenticationFilter;
import com.veterinaria.api_veterinaria.filter.JwtValidationFilter;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        return http.authorizeHttpRequests((authz) -> authz
            .requestMatchers("/api/v1/registro/**").permitAll() // Indica que permite a todos acceder a esta ruta
            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // Indica que permite a todos acceder a esta ruta
            .anyRequest().authenticated()) // Para todas las demas peticiones se requiere autorizacion
            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
            .addFilterBefore(new JwtValidationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
            .csrf(config -> config.disable()) // Deshabilitamos la proteccion CSRF
            .cors(cors ->cors.configurationSource(corsConfigurationSource()))
            // Le indica a Spring que no debe usar sesiones Http para almacenar la autenticacion del usuario
            .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // Permitir todos los origenes
        config.setAllowedOriginPatterns(Arrays.asList("*")); 
        // Permitir todos los metodos
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); 
        // Permitir los headers: Authorization y Content-Type
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        // Permitir credenciales (cookies, autenticacion HTTP, etc.)
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Indica que la configuracion de CORS se va a aplicar en todas las rutas
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
            new CorsFilter(corsConfigurationSource()));

        // Establece el orden del filtro CORS (0 es el más alto)
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE); 

        return bean;
    }
}
