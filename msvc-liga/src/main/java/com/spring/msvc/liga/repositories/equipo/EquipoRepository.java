package com.spring.msvc.liga.repositories.equipo;

import com.spring.msvc.liga.entity.equipo.Equipo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EquipoRepository extends MongoRepository<Equipo, String> {

  Boolean existsByNombre (String name);
}
