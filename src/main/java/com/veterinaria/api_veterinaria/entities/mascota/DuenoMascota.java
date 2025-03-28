package com.veterinaria.api_veterinaria.entities.mascota;

import java.util.List;

import com.veterinaria.api_veterinaria.entities.negocio.Veterinaria;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DuenoMascota {

    @Id
    private Long Id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidoP;

    @Column(nullable = false)
    private String apellidoM;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "veterinaria_id", referencedColumnName = "id", nullable = false)
    private Veterinaria veterinaria;

    @OneToMany(mappedBy = "dueno", cascade = CascadeType.ALL)
    private List<Mascota> mascotas;
}
