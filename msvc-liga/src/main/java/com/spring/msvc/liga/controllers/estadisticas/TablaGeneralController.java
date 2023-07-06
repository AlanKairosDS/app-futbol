package com.spring.msvc.liga.controllers.estadisticas;

import com.spring.common.tools.entity.RestResponse;
import com.spring.msvc.liga.dto.estadisticas.EstadisticasRequest;
import com.spring.msvc.liga.entity.estadisticas.TablaGeneral;
import com.spring.msvc.liga.services.estadisticas.TablaGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:9000", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/tabla-general/api")
public class TablaGeneralController {

  @Autowired
  private TablaGeneralService tablaGeneralService;

  @PostMapping(
          value = "/nueva-tabla",
          produces = {MediaType.APPLICATION_JSON_VALUE},
          consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> nuevaTablaGeneral (
          @RequestBody TablaGeneral tablaGeneral) {
    return tablaGeneralService.nuevaTablaGeneral(tablaGeneral);
  }

  @GetMapping(
          value = "/registrar-equipo/{idTabla}/{idEquipo}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> registrarEquipoTabla (@PathVariable String idTabla,
          @PathVariable String idEquipo) {
    return tablaGeneralService.registrarEquipoTabla(idTabla, idEquipo);
  }

  @PutMapping(
          value = "/actualizar-tabla/{idTabla}",
          produces = {MediaType.APPLICATION_JSON_VALUE},
          consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> actualizarTablaGeneral (
          @RequestBody EstadisticasRequest estadisticasRequest, @PathVariable String idTabla) {
    return tablaGeneralService.actualizarTablaGeneral(estadisticasRequest, idTabla);
  }

  @GetMapping(
          value = "/consultar-tabla",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarTablaGeneral () {
    return tablaGeneralService.consultarTablaGeneral();
  }

  @GetMapping(
          value = "/consultar-tabla/{id}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarTablaGeneralById (@PathVariable String id) {
    return tablaGeneralService.consultarTablaGeneralById(id);
  }
}
