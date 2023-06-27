package com.spring.msvc.elementos.repositories;

import com.spring.msvc.elementos.entity.Equipo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EquipoRepository extends MongoRepository<Equipo, String> {

  Boolean existsByNombre (String name);
}
