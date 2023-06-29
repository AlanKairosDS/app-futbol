package com.spring.msvc.liga.repositories.liga;

import com.spring.msvc.liga.entity.liga.LigaVaronil;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LigaVaronilRepository extends MongoRepository<LigaVaronil, String> {

  Boolean existsByNombre (String nombre);

  Optional<LigaVaronil> findByNombre(String nombre);
}
