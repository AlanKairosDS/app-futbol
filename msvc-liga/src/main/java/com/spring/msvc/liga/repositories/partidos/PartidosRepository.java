package com.spring.msvc.liga.repositories.partidos;

import com.spring.msvc.liga.entity.partidos.Partido;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PartidosRepository extends MongoRepository<Partido, String> {

  Optional<Partido> findByJornada(int jornada);

  Optional<Partido> findByFecha(String fecha);
}
