
package com.veterinaria.api_veterinaria.entities.negocio;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.veterinaria.api_veterinaria.entities.mascota.Mascota;
import com.veterinaria.api_veterinaria.validators.usuario.EmailUnico;

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
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    // @EmailUnico
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    // @Column(columnDefinition = "boolean default true")
    private boolean activo = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", 
    joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id"),
    uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "rol_id"})
    )
    private Set<Rol> roles;

    @OneToMany(mappedBy= "dueno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mascota> mascotas;

    @OneToMany(mappedBy = "dueno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Veterinaria> veterinarias;

    // Estos campos no se mapean a la base de datos, son solo para la logica de negocio
    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean veterinario;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean dueno;

    public boolean isVeterinario() {
        return veterinario;
    }

    public boolean isDueno() {
        return dueno;
    }

}
