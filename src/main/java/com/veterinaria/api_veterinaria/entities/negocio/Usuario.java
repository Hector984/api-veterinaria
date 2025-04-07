
package com.veterinaria.api_veterinaria.entities.negocio;

import java.util.List;
import java.util.Set;

import com.veterinaria.api_veterinaria.entities.mascota.Mascota;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "apellido_paterno", nullable = false)
    private String apellidoP;

    @Column(name = "apellido_materno", nullable = false)
    private String apellidoM;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    public String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", 
    joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> rol;

    @OneToMany(mappedBy= "dueno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mascota> mascotas;

    @OneToMany(mappedBy = "dueno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Veterinaria> veterinarias;
}
