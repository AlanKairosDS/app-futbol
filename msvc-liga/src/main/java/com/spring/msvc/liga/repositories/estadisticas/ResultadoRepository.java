package com.spring.msvc.liga.repositories.estadisticas;

import com.spring.msvc.liga.entity.estadisticas.Resultado;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResultadoRepository extends MongoRepository<Resultado, String> {
}
