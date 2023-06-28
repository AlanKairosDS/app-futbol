package com.spring.msvc.liga.repositories.estadisticas;

import com.spring.msvc.liga.entity.estadisticas.Tarjetas;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TarjetasRepository extends MongoRepository<Tarjetas, String> {

  Optional<Tarjetas> findByFutbolista(String futbolista);
}
