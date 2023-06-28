package com.spring.msvc.liga.services.estadisticas;

import com.spring.common.tools.entity.RestResponse;
import com.spring.msvc.liga.dto.estadisticas.EstadisticasRequest;
import org.springframework.http.ResponseEntity;

public interface TablaGeneralService {

  ResponseEntity<RestResponse<Object>> registrarEquipoTabla (String idEquipo);

  ResponseEntity<RestResponse<Object>> actualizarTablaGeneral (EstadisticasRequest estadisticasRequest);

  ResponseEntity<RestResponse<Object>> consultarTablaGeneral ();

  ResponseEntity<RestResponse<Object>> consultarTablaGeneralById (String id);
}
