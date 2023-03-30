package com.intranet.demo.dto;

import com.intranet.demo.models.entities.Roll;

public @lombok.Data class UsuarioDto {

        Integer idUsuario;
        String nombre;
        String primerApellido;
        String segundoApellido;
        String email;
        String password;
        Integer idRoll;

    public UsuarioDto(Integer idUsuario, String nombre, String primerApellido, String segundoApellido, String email, String password, Integer idRoll) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.email = email;
        this.password = password;
        this.idRoll = idRoll;
    }

    public UsuarioDto(String email) {
        this.email = email;
    }

    public UsuarioDto() {;
    }
}
