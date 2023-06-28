package com.spring.msvc.liga.services.estadisticas;

import com.spring.common.tools.entity.RestResponse;
import com.spring.msvc.liga.dto.estadisticas.EstadisticasRequest;
import org.springframework.http.ResponseEntity;

public interface GolesService {

  ResponseEntity<RestResponse<Object>> agregarActualizarGoles (EstadisticasRequest estadisticasRequest);

  ResponseEntity<RestResponse<Object>> consultarGoles ();

  ResponseEntity<RestResponse<Object>> consultarGolesById (String id);

  ResponseEntity<RestResponse<Object>> consultarGolesByFutbolista (String id);
}
