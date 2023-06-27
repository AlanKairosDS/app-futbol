package com.spring.msvc.liga.repositories.partidos;

import com.spring.msvc.liga.entity.partidos.Partido;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PartidosRepository extends MongoRepository<Partido, String> {
}
