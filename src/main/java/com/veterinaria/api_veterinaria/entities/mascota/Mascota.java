package com.veterinaria.api_veterinaria.entities.mascota;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mascota {

    @Id
    private Long Id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = true)
    private String raza;

    @Column(nullable = true)
    private String especie;

    @Column(nullable = false)
    private Integer edad;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "dueno_id", referencedColumnName = "id", nullable = false)
    private DuenoMascota dueno;
}
