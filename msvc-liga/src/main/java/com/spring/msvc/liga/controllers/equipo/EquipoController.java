package com.spring.msvc.liga.controllers.equipo;

import com.spring.common.tools.entity.RestResponse;
import com.spring.msvc.liga.entity.equipo.Equipo;
import com.spring.msvc.liga.services.equipo.EquipoService;
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
@RequestMapping("/equipo/api")
public class EquipoController {

  @Autowired
  private EquipoService equipoService;

  @PostMapping(
          value = "/registrar-equipo",
          produces = {MediaType.APPLICATION_JSON_VALUE},
          consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> registrarEquipo (@Valid @RequestBody Equipo equipo) {
    return equipoService.registrarEquipo(equipo);
  }

  @PutMapping(
          value = "/actualizar-equipo/{id}",
          produces = {MediaType.APPLICATION_JSON_VALUE},
          consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> actualizarEquipo (@PathVariable String id,
          @Valid @RequestBody Equipo equipo) {
    return equipoService.actualizarEquipo(id, equipo);
  }

  @DeleteMapping(
          value = "/eliminar-equipo/{id}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> eliminarEquipo (@PathVariable String id) {
    return equipoService.eliminarEquipo(id);
  }

  @GetMapping(
          value = "/consultar-equipos",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarEquipos () {
    return equipoService.consultarEquipos();
  }

  @GetMapping(
          value = "/consultar-equipo/{id}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarEquipo (@PathVariable String id) {
    return equipoService.consultarEquipo(id);
  }

  @GetMapping(
          value = "/insertar-futbolista/{idEquipo}/{idFutbolista}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> insertarFutbolista (@PathVariable String idEquipo,
          @PathVariable String idFutbolista) {
    return equipoService.insertarFutbolista(idEquipo, idFutbolista);
  }
}
