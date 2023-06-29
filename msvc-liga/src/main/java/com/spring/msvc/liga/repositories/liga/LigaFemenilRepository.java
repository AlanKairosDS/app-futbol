package com.spring.msvc.liga.repositories.liga;

import com.spring.msvc.liga.entity.liga.LigaFemenil;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LigaFemenilRepository extends MongoRepository<LigaFemenil, String> {

  Boolean existsByNombre (String nombre);

  Optional<LigaFemenil> findByNombre(String nombre);
}
