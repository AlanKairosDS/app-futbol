package com.spring.msvc.liga.services.partidos;

import com.spring.common.tools.entity.RestResponse;
import com.spring.msvc.liga.dto.partidos.PartidoRequest;
import org.springframework.http.ResponseEntity;

public interface PartidoService {

  ResponseEntity<RestResponse<Object>> registrarPartido (PartidoRequest partidoRequest);

  ResponseEntity<RestResponse<Object>> actualizarPartido (String id, PartidoRequest partidoRequest);

  ResponseEntity<RestResponse<Object>> eliminarPartido (String id);

  ResponseEntity<RestResponse<Object>> consultarPartidos ();

  ResponseEntity<RestResponse<Object>> consultarPartidoById (String id);

  ResponseEntity<RestResponse<Object>> consultarPartidoByJornada (String jornada);

  ResponseEntity<RestResponse<Object>> consultarPartidoByFecha (String fecha);
}
