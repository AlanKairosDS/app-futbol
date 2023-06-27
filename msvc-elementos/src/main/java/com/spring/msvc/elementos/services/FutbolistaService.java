package com.spring.msvc.elementos.services;

import com.spring.common.tools.entity.RestResponse;
import com.spring.msvc.elementos.entity.Futbolista;
import org.springframework.http.ResponseEntity;

public interface FutbolistaService {

  ResponseEntity<RestResponse<Object>> registrarFutbolista (Futbolista futbolista);

  ResponseEntity<RestResponse<Object>> actualizarFutbolista (String id, Futbolista futbolista);

  ResponseEntity<RestResponse<Object>> eliminarFutbolista (String id);

  ResponseEntity<RestResponse<Object>> consultarFutbolistas ();

  ResponseEntity<RestResponse<Object>> consultarFutbolista (String id);
}
