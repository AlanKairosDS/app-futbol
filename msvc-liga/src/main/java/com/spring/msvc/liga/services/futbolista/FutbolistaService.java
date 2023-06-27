package com.spring.msvc.liga.services.futbolista;

import com.spring.common.tools.entity.RestResponse;
import com.spring.msvc.liga.entity.futbolista.Futbolista;
import org.springframework.http.ResponseEntity;

public interface FutbolistaService {

  ResponseEntity<RestResponse<Object>> registrarFutbolista (Futbolista futbolista);

  ResponseEntity<RestResponse<Object>> actualizarFutbolista (String id, Futbolista futbolista);

  ResponseEntity<RestResponse<Object>> eliminarFutbolista (String id);

  ResponseEntity<RestResponse<Object>> consultarFutbolistas ();

  ResponseEntity<RestResponse<Object>> consultarFutbolista (String id);
}
