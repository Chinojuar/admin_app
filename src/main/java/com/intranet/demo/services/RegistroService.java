package com.intranet.demo.services;

import com.intranet.demo.models.entities.Usuario;
import com.intranet.demo.repositories.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RegistroService {

    @Autowired
    RegistroRepository registroRepository;

    public Optional<Usuario> findByUserName(String nombreUsuario){
       return registroRepository.findByNombreUsuario(nombreUsuario);
    }

    public Optional<Usuario> findByEmail(String email){
        return registroRepository.findByEmail(email);
    }

    public Optional<Usuario> traerUsuario(Integer idUsuario){
        return registroRepository.findById(idUsuario);
    }

    public boolean existByUserName(String nombreUsuario){
        return registroRepository.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existByEmail(String email){
        return registroRepository.existsByEmail(email);
    }

    public Usuario guardaUsuario(Usuario usuario){
        return registroRepository.save(usuario);
    }
}
