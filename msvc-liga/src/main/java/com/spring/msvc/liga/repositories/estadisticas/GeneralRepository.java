package com.spring.msvc.liga.repositories.estadisticas;

import com.spring.msvc.liga.entity.estadisticas.TablaGeneral;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GeneralRepository extends MongoRepository<TablaGeneral, String> {
}
