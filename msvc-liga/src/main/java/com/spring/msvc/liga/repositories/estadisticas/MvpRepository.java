package com.spring.msvc.liga.repositories.estadisticas;

import com.spring.msvc.liga.entity.estadisticas.JugadorMvp;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MvpRepository extends MongoRepository<JugadorMvp, String> {

  Optional<JugadorMvp> findByJornada(String jornada);
}
