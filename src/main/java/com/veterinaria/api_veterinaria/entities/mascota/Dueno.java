package com.veterinaria.api_veterinaria.entities.mascota;

import java.time.ZonedDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Entity
@Data
public class Dueno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "apellido_paterno", nullable = false)
    private String apellidoP;

    @Column(name = "apellido_materno", nullable = false)
    private String apellidoM;

    private String telefono;

    @Column(unique = true)
    private String email;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private ZonedDateTime fechaCreacion;

    @Column(name = "fecha_modificacion", nullable = true, updatable = false)
    private ZonedDateTime fechaModificacion;

    @OneToMany(mappedBy = "dueno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mascota> mascotas; // Relación con la entidad Mascota

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = ZonedDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaModificacion = ZonedDateTime.now();
    }
}
