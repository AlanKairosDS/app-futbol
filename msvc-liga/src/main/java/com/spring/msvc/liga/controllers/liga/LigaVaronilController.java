package com.spring.msvc.liga.controllers.liga;

import com.spring.common.tools.entity.RestResponse;
import com.spring.msvc.liga.entity.liga.LigaVaronil;
import com.spring.msvc.liga.services.liga.LigaVaronilService;
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
@RequestMapping("/liga-varonil/api")
public class LigaVaronilController {

  @Autowired
  private LigaVaronilService ligaVaronilService;

  @PostMapping(
          value = "/nueva-liga",
          produces = {MediaType.APPLICATION_JSON_VALUE},
          consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> nuevaLiga (@RequestBody LigaVaronil ligaVaronil) {
    return ligaVaronilService.nuevaLiga(ligaVaronil);
  }

  @GetMapping(
          value = "/consultar-ligas",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarLiga () {
    return ligaVaronilService.consultarLiga();
  }

  @GetMapping(
          value = "/consultar-liga-id/{id}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarLigaById (@PathVariable String id) {
    return ligaVaronilService.consultarLigaById(id);
  }

  @GetMapping(
          value = "/consultar-liga-nombre/{nombre}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarLigaByName (@PathVariable String nombre) {
    return ligaVaronilService.consultarLigaByName(nombre);
  }

  @GetMapping(
          value = "/agregar-equipo-liga/{idLiga}/{idEquipo}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> agregarEquipos (@PathVariable String idLiga,
          @PathVariable String idEquipo) {
    return ligaVaronilService.agregarEquipos(idLiga, idEquipo);
  }

  @GetMapping(
          value = "/agregar-partido-liga/{idLiga}/{idPartido}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> agregarPartidos (@PathVariable String idLiga,
          @PathVariable String idPartido) {
    return ligaVaronilService.agregarPartidos(idLiga, idPartido);
  }

  @GetMapping(
          value = "/agregar-tabla-liga/{idLiga}/{idTabla}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> agregarTablaGeneral (@PathVariable String idLiga,
          @PathVariable String idTabla) {
    return ligaVaronilService.agregarTablaGeneral(idLiga, idTabla);
  }

  @GetMapping(
          value = "/agregar-resultado-liga/{idLiga}/{idResultado}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> agregarResultados (@PathVariable String idLiga,
          @PathVariable String idResultado) {
    return ligaVaronilService.agregarResultados(idLiga, idResultado);
  }

  @GetMapping(
          value = "/agregar-goles-liga/{idLiga}/{idGoles}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> agregarGolesAsistencias (@PathVariable String idLiga,
          @PathVariable String idGoles) {
    return ligaVaronilService.agregarGolesAsistencias(idLiga, idGoles);
  }

  @GetMapping(
          value = "/agregar-tarjetas-liga/{idLiga}/{idTarjetas}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> agregarTarjetas (@PathVariable String idLiga,
          @PathVariable String idTarjetas) {
    return ligaVaronilService.agregarTarjetas(idLiga, idTarjetas);
  }

  @GetMapping(
          value = "/agregar-mvp-liga/{idLiga}/{idMvp}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> agregarMvp (@PathVariable String idLiga, @PathVariable String idMvp) {
    return ligaVaronilService.agregarMvp(idLiga, idMvp);
  }
}
