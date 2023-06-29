package com.spring.msvc.liga.controllers.estadisticas;

import com.spring.common.tools.entity.RestResponse;
import com.spring.msvc.liga.dto.estadisticas.ResultadoRequest;
import com.spring.msvc.liga.entity.estadisticas.Resultado;
import com.spring.msvc.liga.services.estadisticas.ResultadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:9000", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/resultado/api")
public class ResultadoController {

  @Autowired
  private ResultadoService resultadoService;

  @PostMapping(
          value = "/registrar-resultado",
          produces = {MediaType.APPLICATION_JSON_VALUE},
          consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> registrarResultado (@RequestBody ResultadoRequest resultadoRequest) {
    return resultadoService.registrarResultado(resultadoRequest);
  }

  @GetMapping(
          value = "/consultar-resultados",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarResultados () {
    return resultadoService.consultarResultados();
  }

  @GetMapping(
          value = "/consultar-resultado/{id}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarResultadoById (@PathVariable String id) {
    return resultadoService.consultarResultadoById(id);
  }
}
