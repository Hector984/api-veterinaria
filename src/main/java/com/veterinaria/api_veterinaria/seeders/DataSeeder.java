package com.veterinaria.api_veterinaria.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.veterinaria.api_veterinaria.entities.negocio.Rol;
import com.veterinaria.api_veterinaria.repositories.negocio.RolRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private RolRepository rolRepository;

    public void run(String ... args) throws Exception {
        // Aquí puedes agregar la lógica para sembrar datos iniciales en la base de datos
        // Por ejemplo, crear roles predeterminados
        if(rolRepository.count() == 0) {
            Rol rolAdmin = new Rol("ADMINISTRADOR");
            Rol rolVeterinario = new Rol("VETERINARIO");
            Rol rolUser = new Rol("DUENO_MASCOTA");
        
            rolRepository.save(rolAdmin);
            rolRepository.save(rolVeterinario);
            rolRepository.save(rolUser); 
        }
    }
}
