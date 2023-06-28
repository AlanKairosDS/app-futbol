package com.spring.msvc.liga.repositories.estadisticas;

import com.spring.msvc.liga.entity.estadisticas.GolesAsistencias;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GolesRepository extends MongoRepository<GolesAsistencias, String> {

  Optional<GolesAsistencias> findByFutbolista (String futbolista);
}
