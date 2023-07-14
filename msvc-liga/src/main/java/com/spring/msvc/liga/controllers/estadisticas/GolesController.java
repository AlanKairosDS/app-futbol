package com.spring.msvc.liga.controllers.estadisticas;

import com.spring.common.tools.entity.RestResponse;
import com.spring.msvc.liga.dto.estadisticas.EstadisticasRequest;
import com.spring.msvc.liga.services.estadisticas.GolesService;
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

@CrossOrigin(origins = "${msvcliga.app.origin}", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/goles-asistencias/api")
public class GolesController {

  @Autowired
  private GolesService golesService;

  @PostMapping(
          value = "/agregar-actualizar-goles",
          produces = {MediaType.APPLICATION_JSON_VALUE},
          consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> agregarActualizarGoles (
          @RequestBody EstadisticasRequest estadisticasRequest) {
    return golesService.agregarActualizarGoles(estadisticasRequest);
  }

  @GetMapping(
          value = "/consultar-goles",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarGoles () {
    return golesService.consultarGoles();
  }

  @GetMapping(
          value = "/consultar-goles-id/{id}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarGolesById (@PathVariable String id) {
    return golesService.consultarGolesById(id);
  }

  @GetMapping(
          value = "/consultar-goles-futbolista/{id}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarGolesByFutbolista (@PathVariable String id) {
    return golesService.consultarGolesByFutbolista(id);
  }
}
