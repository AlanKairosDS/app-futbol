package com.spring.msvc.liga.services.estadisticas;

import com.spring.common.tools.entity.RestResponse;
import com.spring.msvc.liga.dto.estadisticas.EstadisticasRequest;
import org.springframework.http.ResponseEntity;

public interface TarjetasService {

  ResponseEntity<RestResponse<Object>> agregarActualizarTarjetas (EstadisticasRequest estadisticasRequest);

  ResponseEntity<RestResponse<Object>> consultarTarjetas ();

  ResponseEntity<RestResponse<Object>> consultarTarjetasById (String id);

  ResponseEntity<RestResponse<Object>> consultarTarjetasByFutbolista (String id);
}
