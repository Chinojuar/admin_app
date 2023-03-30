package com.intranet.demo.repositories;

import com.intranet.demo.enums.RolNombre;
import com.intranet.demo.models.entities.Roll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Roll, Integer> {
Optional<Roll> getByRolNombre(RolNombre nombre);

}
