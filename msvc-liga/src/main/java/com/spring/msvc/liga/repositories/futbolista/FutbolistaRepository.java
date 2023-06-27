package com.spring.msvc.liga.repositories.futbolista;

import com.spring.msvc.liga.entity.futbolista.Futbolista;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FutbolistaRepository extends MongoRepository<Futbolista, String> {

  Boolean existsByNombre (String name);
}
