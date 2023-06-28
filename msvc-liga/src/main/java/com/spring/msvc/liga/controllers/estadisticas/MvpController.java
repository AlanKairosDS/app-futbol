package com.spring.msvc.liga.controllers.estadisticas;

import com.spring.common.tools.entity.RestResponse;
import com.spring.msvc.liga.dto.estadisticas.EstadisticasRequest;
import com.spring.msvc.liga.services.estadisticas.MvpService;
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

@CrossOrigin("*")
@RestController
@RequestMapping("/mvp/api")
public class MvpController {

  @Autowired
  private MvpService mvpService;

  @PostMapping(
          value = "/agregar-actualizar-mvp",
          produces = {MediaType.APPLICATION_JSON_VALUE},
          consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> agregarActualizarMvp (
          @RequestBody EstadisticasRequest estadisticasRequest) {
    return mvpService.agregarActualizarMvp(estadisticasRequest);
  }

  @GetMapping(
          value = "/consultar-mvp",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarMvp () {
    return mvpService.consultarMvp();
  }

  @GetMapping(
          value = "/consultar-mvp-id/{id}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarMvpById (@PathVariable String id) {
    return mvpService.consultarMvpById(id);
  }

  @GetMapping(
          value = "/consultar-mvp-futbolista/{id}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarMvpByFutbolista (@PathVariable String id) {
    return mvpService.consultarMvpByFutbolista(id);
  }
}
