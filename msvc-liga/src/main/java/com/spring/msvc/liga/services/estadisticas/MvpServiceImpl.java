package com.spring.msvc.liga.services.estadisticas;

import com.spring.common.tools.entity.RestResponse;
import com.spring.common.tools.service.UtilService;
import com.spring.msvc.liga.dto.estadisticas.EstadisticasRequest;
import com.spring.msvc.liga.dto.estadisticas.MvpResponse;
import com.spring.msvc.liga.entity.estadisticas.JugadorMvp;
import com.spring.msvc.liga.entity.futbolista.Futbolista;
import com.spring.msvc.liga.repositories.estadisticas.MvpRepository;
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
public class MvpServiceImpl implements MvpService {

  private static final Logger LOGGER = LogManager.getLogger("liga");
  private static final String EXITO = "Proceso ejecutado correctamente";

  @Autowired
  private MvpRepository mvpRepository;

  @Autowired
  private FutbolistaRepository futbolistaRepository;

  @Autowired
  private UtilService utilService;

  @Override
  public ResponseEntity<RestResponse<Object>> agregarActualizarMvp (EstadisticasRequest estadisticasRequest) {
    JugadorMvp jugadorMvp = JugadorMvp.builder()
            .futbolista(estadisticasRequest.getIdFutbolista())
            .jornada(estadisticasRequest.getJornada())
            .build();

    mvpRepository.save(jugadorMvp);

    return new UtilService().armarRespuesta(
            HttpStatus.OK.value(),
            EXITO,
            "Se registro el jugador mvp de forma correcta",
            true,
            null,
            HttpStatus.OK
    );
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarMvp () {
    List<JugadorMvp> jugadorMvpList = mvpRepository.findAll();
    List<MvpResponse> mvpResponseList = new ArrayList<>();

    if (!jugadorMvpList.isEmpty()) {
      for (JugadorMvp jugadorMvp : jugadorMvpList) {
        Optional<Futbolista> futbolistaOptional = futbolistaRepository.findById(jugadorMvp.getFutbolista());

        MvpResponse mvpResponse = MvpResponse.builder()
                .id(jugadorMvp.getId())
                .futbolista(futbolistaOptional.get())
                .jornada(jugadorMvp.getJornada())
                .build();

        mvpResponseList.add(mvpResponse);
      }

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se consulta la tabla de mvp de forma correcta",
              true,
              mvpResponseList,
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al consultar la tabla de mvp",
              "No se encontro ningun registro",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }

  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarMvpById (String id) {
    Optional<JugadorMvp> jugadorMvpOptional = mvpRepository.findById(id);

    if (jugadorMvpOptional.isPresent()) {
      Optional<Futbolista> futbolistaOptional = futbolistaRepository.findById(jugadorMvpOptional.get().getFutbolista());

      MvpResponse mvpResponse = MvpResponse.builder()
              .id(jugadorMvpOptional.get().getId())
              .futbolista(futbolistaOptional.get())
              .jornada(jugadorMvpOptional.get().getJornada())
              .build();

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se consulta la tabla de mvp por id de forma correcta",
              true,
              mvpResponse,
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al consultar la tabla de mvp por id",
              "El id que se requiere consultar no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarMvpByJornada (String id) {
    Optional<JugadorMvp> jugadorMvpOptional = mvpRepository.findByJornada(id);

    if (jugadorMvpOptional.isPresent()) {
      Optional<Futbolista> futbolistaOptional = futbolistaRepository.findById(jugadorMvpOptional.get().getFutbolista());

      MvpResponse mvpResponse = MvpResponse.builder()
              .id(jugadorMvpOptional.get().getId())
              .futbolista(futbolistaOptional.get())
              .jornada(jugadorMvpOptional.get().getJornada())
              .build();

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se consulta la tabla de mvp por jornada de forma correcta",
              true,
              mvpResponse,
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al consultar la tabla de mvp por jornada",
              "La jornada que se requiere consultar no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }
}
