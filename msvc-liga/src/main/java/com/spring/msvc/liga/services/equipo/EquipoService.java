package com.spring.msvc.liga.services.equipo;

import com.spring.common.tools.entity.RestResponse;
import com.spring.msvc.liga.entity.equipo.Equipo;
import org.springframework.http.ResponseEntity;

public interface EquipoService {

  ResponseEntity<RestResponse<Object>> registrarEquipo (Equipo equipo);

  ResponseEntity<RestResponse<Object>> actualizarEquipo (String id, Equipo equipo);

  ResponseEntity<RestResponse<Object>> eliminarEquipo (String id);

  ResponseEntity<RestResponse<Object>> consultarEquipos ();

  ResponseEntity<RestResponse<Object>> consultarEquipo (String id);

  ResponseEntity<RestResponse<Object>> insertarFutbolista (String idEquipo, String idFutbolista);
}
