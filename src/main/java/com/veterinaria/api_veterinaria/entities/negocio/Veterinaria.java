package com.veterinaria.api_veterinaria.entities.negocio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "veterinarias")
public class Veterinaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(nullable = false, unique = true)
    private String alias;

    @Column(nullable = false)
    private String dirección;

    @Column(nullable = true)
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "dueno_id", referencedColumnName = "id", nullable = false)
    private Usuario dueno;

    @Column(nullable = false)
    private int activa;


}
