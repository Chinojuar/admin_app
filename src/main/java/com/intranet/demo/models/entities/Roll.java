package com.intranet.demo.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.intranet.demo.enums.RolNombre;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "roll")
public  @lombok.Data class Roll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_roll")
    private Integer idRoll;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name="rol_nombre")
    private RolNombre rolNombre;


    public Roll() {

    }

    public Roll(@NotNull RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
}
