package com.spring.msvc.liga.controllers.partidos;

import com.spring.common.tools.entity.RestResponse;
import com.spring.msvc.liga.dto.partidos.PartidoRequest;
import com.spring.msvc.liga.services.partidos.PartidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "${msvcliga.app.origin}", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/partido/api")
public class PartidoController {

  @Autowired
  private PartidoService partidoService;

  @PostMapping(
          value = "/registrar-partido",
          produces = {MediaType.APPLICATION_JSON_VALUE},
          consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> registrarPartido (@Valid @RequestBody PartidoRequest partidoRequest) {
    return partidoService.registrarPartido(partidoRequest);
  }

  @PutMapping(
          value = "/actualizar-partido/{id}",
          produces = {MediaType.APPLICATION_JSON_VALUE},
          consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> actualizarPartido (@PathVariable String id,
          @Valid @RequestBody PartidoRequest partidoRequest) {
    return partidoService.actualizarPartido(id, partidoRequest);
  }

  @DeleteMapping(
          value = "/eliminar-partido/{id}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> eliminarPartido (@PathVariable String id) {
    return partidoService.eliminarPartido(id);
  }

  @GetMapping(
          value = "/consultar-partidos",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarPartidos () {
    return partidoService.consultarPartidos();
  }

  @GetMapping(
          value = "/consultar-partido-id/{id}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarPartidoById (@PathVariable String id) {
    return partidoService.consultarPartidoById(id);
  }

  @GetMapping(
          value = "/consultar-partido-jornada/{jornada}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarPartidoByJornada (@PathVariable String jornada) {
    return partidoService.consultarPartidoByJornada(jornada);
  }

  @GetMapping(
          value = "/consultar-partido-fecha/{fecha}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarPartidoByFecha (@PathVariable String fecha) {
    return partidoService.consultarPartidoByFecha(fecha);
  }

  @GetMapping(
          value = "/consultar-partido-hora/{hora}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarPartidoByHora (@PathVariable String hora) {
    return partidoService.consultarPartidoByHora(hora);
  }
}
