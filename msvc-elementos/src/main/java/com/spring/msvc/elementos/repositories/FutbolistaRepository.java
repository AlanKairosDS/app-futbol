package com.spring.msvc.elementos.repositories;

import com.spring.msvc.elementos.entity.Futbolista;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FutbolistaRepository extends MongoRepository<Futbolista, String> {

  Boolean existsByNombre (String name);
}
