package com.intranet.demo.dto;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public @lombok.Data class JwtDTO {
    private String token;
    private String nombreUsuario;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtDTO(String token, String nombreUsuario, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.nombreUsuario = nombreUsuario;
        this.authorities = authorities;
    }

}
