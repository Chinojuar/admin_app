package com.intranet.demo.models.entities;
import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public @lombok.Data class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    private String nombre;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @Column(name = "primer_apellido")
    private String primerApellido;

    @Column(name = "segundo_apellido")
    private String segundoApellido;

    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name= "roll_usuario", joinColumns = @JoinColumn(name="usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Roll> roles = new HashSet<>();

    public Usuario(String nombre,String nombreUsuario, String primerApellido, String segundoApellido, String email, String password) {
        super();
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.email = email;
        this.password = password;
    }

    public Usuario() {

    }

    public Usuario(String nombre, String email, String nombreUsuario, String password) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
    }
}
