package com.spring.msvc.liga.controllers.futbolista;

import com.spring.common.tools.entity.RestResponse;
import com.spring.msvc.liga.entity.futbolista.Futbolista;
import com.spring.msvc.liga.services.futbolista.FutbolistaService;
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
@RequestMapping("/futbolista/api")
public class FutbolistaController {

  @Autowired
  private FutbolistaService futbolistaService;

  @PostMapping(
          value = "/registrar-futbolista",
          produces = {MediaType.APPLICATION_JSON_VALUE},
          consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> registrarFutbolista (@Valid @RequestBody Futbolista futbolista) {
    return futbolistaService.registrarFutbolista(futbolista);
  }

  @PutMapping(
          value = "/actualizar-futbolista/{id}",
          produces = {MediaType.APPLICATION_JSON_VALUE},
          consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> actualizarFutbolista (@PathVariable String id,
          @Valid @RequestBody Futbolista futbolista) {
    return futbolistaService.actualizarFutbolista(id, futbolista);
  }

  @DeleteMapping(
          value = "/eliminar-futbolista/{id}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> eliminarFutbolista (@PathVariable String id) {
    return futbolistaService.eliminarFutbolista(id);
  }

  @GetMapping(
          value = "/consultar-futbolistas",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarFutbolistas () {
    return futbolistaService.consultarFutbolistas();
  }

  @GetMapping(
          value = "/consultar-futbolista/{id}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarFutbolista (@PathVariable String id) {
    return futbolistaService.consultarFutbolista(id);
  }
}
