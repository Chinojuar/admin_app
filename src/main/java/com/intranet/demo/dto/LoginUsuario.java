package com.intranet.demo.dto;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

public @lombok.Data class LoginUsuario {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}