package com.spring.msvc.liga.services.estadisticas;

import com.spring.common.tools.entity.RestResponse;
import com.spring.common.tools.service.UtilService;
import com.spring.msvc.liga.dto.estadisticas.EstadisticasRequest;
import com.spring.msvc.liga.dto.estadisticas.GolesResponse;
import com.spring.msvc.liga.entity.estadisticas.GolesAsistencias;
import com.spring.msvc.liga.entity.futbolista.Futbolista;
import com.spring.msvc.liga.repositories.estadisticas.GolesRepository;
import com.spring.msvc.liga.repositories.futbolista.FutbolistaRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GolesServiceImpl implements GolesService {

  private static final Logger LOGGER = LogManager.getLogger("liga");
  private static final String EXITO = "Proceso ejecutado correctamente";

  @Autowired
  private GolesRepository golesRepository;

  @Autowired
  private FutbolistaRepository futbolistaRepository;

  @Autowired
  private UtilService utilService;

  @Override
  public ResponseEntity<RestResponse<Object>> agregarActualizarGoles (EstadisticasRequest estadisticasRequest) {
    Optional<GolesAsistencias> golesAsistenciasOptional =
            golesRepository.findByFutbolista(estadisticasRequest.getIdFutbolista());

    if (golesAsistenciasOptional.isPresent()) {
      GolesAsistencias actualizaGoles = golesAsistenciasOptional.get();

      actualizaGoles.setGoles(actualizaGoles.getGoles() + estadisticasRequest.getGoles());
      actualizaGoles.setAsistencias(actualizaGoles.getAsistencias() + estadisticasRequest.getAsitencias());

      golesRepository.save(actualizaGoles);

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se actualizaron las estadisticas del futbolista de forma correcta",
              true,
              null,
              HttpStatus.OK
      );
    }
    else {
      GolesAsistencias registraGoles = GolesAsistencias.builder()
              .futbolista(estadisticasRequest.getIdFutbolista())
              .goles(estadisticasRequest.getGoles())
              .asistencias(estadisticasRequest.getAsitencias())
              .build();

      golesRepository.save(registraGoles);

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se registraron las estadisticas del futbolista de forma correcta",
              true,
              null,
              HttpStatus.OK
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarGoles () {
    List<GolesAsistencias> golesAsistenciasList = golesRepository.findAll();
    List<GolesResponse> golesResponseList = new ArrayList<>();

    if (!golesAsistenciasList.isEmpty()) {
      for (GolesAsistencias golesAsistencias : golesAsistenciasList) {
        Optional<Futbolista> futbolistaOptional = futbolistaRepository.findById(golesAsistencias.getFutbolista());

        GolesResponse golesResponse = GolesResponse.builder()
                .id(golesAsistencias.getId())
                .futbolista(futbolistaOptional.get())
                .goles(golesAsistencias.getGoles())
                .asistencias(golesAsistencias.getAsistencias())
                .build();

        golesResponseList.add(golesResponse);
      }

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se consultan las estadisticas de forma correcta",
              true,
              golesResponseList,
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al consultar las estadisticas",
              "No se encontro ningun registro",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarGolesById (String id) {
    Optional<GolesAsistencias> golesAsistenciasOptional = golesRepository.findById(id);

    if (golesAsistenciasOptional.isPresent()) {
      Optional<Futbolista> futbolistaOptional =
              futbolistaRepository.findById(golesAsistenciasOptional.get().getFutbolista());

      GolesResponse golesResponse = GolesResponse.builder()
              .id(golesAsistenciasOptional.get().getId())
              .futbolista(futbolistaOptional.get())
              .goles(golesAsistenciasOptional.get().getGoles())
              .asistencias(golesAsistenciasOptional.get().getAsistencias())
              .build();

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se consultan las estadisticas por id de forma correcta",
              true,
              golesResponse,
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al consultar las estadisticas por id",
              "El id que se requiere consultar no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarGolesByFutbolista (String id) {
    Optional<GolesAsistencias> golesAsistenciasOptional = golesRepository.findByFutbolista(id);

    if (golesAsistenciasOptional.isPresent()) {
      Optional<Futbolista> futbolistaOptional =
              futbolistaRepository.findById(golesAsistenciasOptional.get().getFutbolista());

      GolesResponse golesResponse = GolesResponse.builder()
              .id(golesAsistenciasOptional.get().getId())
              .futbolista(futbolistaOptional.get())
              .goles(golesAsistenciasOptional.get().getGoles())
              .asistencias(golesAsistenciasOptional.get().getAsistencias())
              .build();

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se consultan las estadisticas por futbolista de forma correcta",
              true,
              golesResponse,
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al consultar las estadisticas por futbolista",
              "El futbolista que se requiere consultar no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }
}
