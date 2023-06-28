package com.spring.msvc.liga.services.estadisticas;

import com.spring.common.tools.entity.RestResponse;
import com.spring.msvc.liga.entity.estadisticas.Resultado;
import org.springframework.http.ResponseEntity;

public interface ResultadoService {

  ResponseEntity<RestResponse<Object>> registrarResultado (Resultado resultado);

  ResponseEntity<RestResponse<Object>> consultarResultados ();

  ResponseEntity<RestResponse<Object>> consultarResultadoById (String id);
}
