package com.spring.msvc.liga.services.liga;

import com.spring.common.tools.entity.RestResponse;
import com.spring.msvc.liga.entity.liga.LigaVaronil;
import org.springframework.http.ResponseEntity;

public interface LigaVaronilService {

  ResponseEntity<RestResponse<Object>> nuevaLiga (LigaVaronil ligaVaronil);

  ResponseEntity<RestResponse<Object>> consultarLiga ();

  ResponseEntity<RestResponse<Object>> consultarLigaById (String id);

  ResponseEntity<RestResponse<Object>> consultarLigaByName (String nombre);

  ResponseEntity<RestResponse<Object>> agregarEquipos (String idLiga, String idEquipo);

  ResponseEntity<RestResponse<Object>> agregarPartidos (String idLiga, String idPartido);

  ResponseEntity<RestResponse<Object>> agregarTablaGeneral (String idLiga, String idTabla);

  ResponseEntity<RestResponse<Object>> agregarResultados (String idLiga, String idResultado);

  ResponseEntity<RestResponse<Object>> agregarGolesAsistencias (String idLiga, String idGoles);

  ResponseEntity<RestResponse<Object>> agregarTarjetas (String idLiga, String idTarjetas);

  ResponseEntity<RestResponse<Object>> agregarMvp (String idLiga, String idMvp);
}
