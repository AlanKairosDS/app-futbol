package com.spring.msvc.liga.services.estadisticas;

import com.spring.common.tools.entity.RestResponse;
import com.spring.msvc.liga.dto.estadisticas.EstadisticasRequest;
import org.springframework.http.ResponseEntity;

public interface MvpService {

  ResponseEntity<RestResponse<Object>> agregarActualizarMvp (EstadisticasRequest estadisticasRequest);

  ResponseEntity<RestResponse<Object>> consultarMvp ();

  ResponseEntity<RestResponse<Object>> consultarMvpById (String id);

  ResponseEntity<RestResponse<Object>> consultarMvpByJornada (String id);
}
