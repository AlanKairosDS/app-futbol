package com.spring.msvc.liga.services.estadisticas;

import com.spring.common.tools.entity.RestResponse;
import com.spring.msvc.liga.dto.estadisticas.EstadisticasRequest;
import com.spring.msvc.liga.entity.estadisticas.TablaGeneral;
import org.springframework.http.ResponseEntity;

public interface TablaGeneralService {

  ResponseEntity<RestResponse<Object>> nuevaTablaGeneral (TablaGeneral tablaGeneral);

  ResponseEntity<RestResponse<Object>> registrarEquipoTabla (String idTabla, String idEquipo);

  ResponseEntity<RestResponse<Object>> actualizarTablaGeneral (EstadisticasRequest estadisticasRequest, String idTabla);

  ResponseEntity<RestResponse<Object>> consultarTablaGeneral ();

  ResponseEntity<RestResponse<Object>> consultarTablaGeneralById (String id);
}
