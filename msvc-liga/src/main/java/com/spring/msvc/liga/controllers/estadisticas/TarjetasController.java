package com.spring.msvc.liga.controllers.estadisticas;

import com.spring.common.tools.entity.RestResponse;
import com.spring.msvc.liga.dto.estadisticas.EstadisticasRequest;
import com.spring.msvc.liga.services.estadisticas.TarjetasService;
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
@RequestMapping("/tarjetas/api")
public class TarjetasController {

  @Autowired
  private TarjetasService tarjetasService;

  @PostMapping(
          value = "/agregar-actualizar-tarjetas",
          produces = {MediaType.APPLICATION_JSON_VALUE},
          consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> agregarActualizarTarjetas (
          @RequestBody EstadisticasRequest estadisticasRequest) {
    return tarjetasService.agregarActualizarTarjetas(estadisticasRequest);
  }

  @GetMapping(
          value = "/consultar-tarjetas",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarTarjetas () {
    return tarjetasService.consultarTarjetas();
  }

  @GetMapping(
          value = "/consultar-tarjetas-id/{id}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarTarjetasById (@PathVariable String id) {
    return tarjetasService.consultarTarjetasById(id);
  }

  @GetMapping(
          value = "/consultar-tarjetas-futbolista/{id}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarTarjetasByFutbolista (@PathVariable String id) {
    return tarjetasService.consultarTarjetasByFutbolista(id);
  }
}
