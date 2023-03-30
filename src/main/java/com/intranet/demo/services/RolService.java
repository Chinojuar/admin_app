package com.intranet.demo.services;

import com.intranet.demo.enums.RolNombre;
import com.intranet.demo.models.entities.Roll;
import com.intranet.demo.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

public Optional<Roll> getByNombre(RolNombre nombre){

    return rolRepository.getByRolNombre(nombre);
}

public void guardar(Roll roll){
     rolRepository.save(roll);
};
}
