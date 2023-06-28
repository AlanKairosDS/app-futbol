package com.spring.msvc.liga.repositories.partidos;

import com.spring.msvc.liga.entity.partidos.Partido;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PartidosRepository extends MongoRepository<Partido, String> {

  List<Partido> findByJornada(int jornada);

  List<Partido> findByFecha(String fecha);
}
