package com.intranet.demo.web.controllers;
import com.intranet.demo.dto.JwtDTO;
import com.intranet.demo.dto.LoginUsuario;
import com.intranet.demo.dto.NuevoUsuario;
import com.intranet.demo.enums.RolNombre;
import com.intranet.demo.models.entities.Roll;
import com.intranet.demo.models.entities.Usuario;
import com.intranet.demo.security.JWT.JwtProvider;
import com.intranet.demo.services.RegistroService;
import com.intranet.demo.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passworEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RegistroService registroService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;


    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrarUsuario(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity("errorCode:101",HttpStatus.BAD_REQUEST);
        if(registroService.existByUserName(nuevoUsuario.getNombreUsuario()) || registroService.existByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity("errorCode:304",HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getEmail(), nuevoUsuario.getNombreUsuario(),
                        passworEncoder.encode(nuevoUsuario.getPassword()));
        Set<Roll> roles = new HashSet<>();
        roles.add(rolService.getByNombre(RolNombre.ROLE_USER).get());
        if(nuevoUsuario.getRoles().contains("administrador"))
            roles.add(rolService.getByNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        registroService.guardaUsuario(usuario);
        return new ResponseEntity(usuario,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity("campos vacíos o email inválido", HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUsuario.getEmail(), loginUsuario.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<JwtDTO>(jwtDTO, HttpStatus.OK);
    }
}
