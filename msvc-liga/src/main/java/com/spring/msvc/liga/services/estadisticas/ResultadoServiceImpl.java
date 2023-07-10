package com.spring.msvc.liga.services.estadisticas;

import com.spring.common.tools.entity.RestResponse;
import com.spring.common.tools.service.UtilService;
import com.spring.msvc.liga.dto.estadisticas.EstadisticasRequest;
import com.spring.msvc.liga.dto.estadisticas.ResultadoRequest;
import com.spring.msvc.liga.entity.estadisticas.Resultado;
import com.spring.msvc.liga.entity.estadisticas.ResultadoEstad;
import com.spring.msvc.liga.entity.partidos.Partido;
import com.spring.msvc.liga.repositories.estadisticas.ResultadoRepository;
import com.spring.msvc.liga.repositories.partidos.PartidosRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResultadoServiceImpl implements ResultadoService {

  private static final Logger LOGGER = LogManager.getLogger("liga");
  private static final String EXITO = "Proceso ejecutado correctamente";

  @Autowired
  private ResultadoRepository resultadoRepository;

  @Autowired
  private PartidosRepository partidosRepository;

  @Autowired
  private GolesServiceImpl golesServiceImpl;

  @Autowired
  private MvpServiceImpl mvpServiceImpl;

  @Autowired
  private TarjetasServiceImpl tarjetasServiceImpl;

  @Autowired
  private TablaGeneralServiceImpl tablaGeneralServiceImpl;

  @Autowired
  private UtilService utilService;

  @Override
  public ResponseEntity<RestResponse<Object>> registrarResultado (ResultadoRequest resultadoRequest) {
    Optional<Partido> partidoOptional = partidosRepository.findById(resultadoRequest.getPartido());

    if (partidoOptional.isPresent()) {
      Resultado resultado = Resultado.builder() 
              .partido(partidoOptional.get())
              .jornada(resultadoRequest.getJornada())
              .marcadorLocal(resultadoRequest.getMarcadorLocal())
              .marcadorVisita(resultadoRequest.getMarcadorVisita())
              .idTablaEquipoLocal(resultadoRequest.getIdTablaEquipoLocal())
              .idTablaEquipoVisita(resultadoRequest.getIdTablaEquipoVisita())
              .ganador(resultadoRequest.getGanador())
              .estadisticas(resultadoRequest.getEstadisticas())
              .build();

      resultadoRepository.save(resultado);

      actualizarTablaGoleo(resultadoRequest);
      actualizarTablaTarjetas(resultadoRequest);
      actualizarTablaMvp(resultadoRequest);
      actualizarTablaGeneralLocal(resultadoRequest, partidoOptional.get());
      actualizarTablaGeneralVisita(resultadoRequest, partidoOptional.get());

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se registro el resultado de forma correcta",
              true,
              null,
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al registrar el resultado",
              "El partido que quieres registrar no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  public void actualizarTablaGoleo (ResultadoRequest resultadoRequest) {
    for (ResultadoEstad resultadoEstad : resultadoRequest.getEstadisticas()) {
      if (resultadoEstad.getGoles() > 0 || resultadoEstad.getAsistencias() > 0) {
        EstadisticasRequest estadisticasRequest = EstadisticasRequest.builder()
                .idFutbolista(resultadoEstad.getIdFutbolista())
                .goles(resultadoEstad.getGoles())
                .asitencias(resultadoEstad.getAsistencias())
                .build();

        golesServiceImpl.agregarActualizarGoles(estadisticasRequest);
      }
    }
  }

  public void actualizarTablaTarjetas (ResultadoRequest resultadoRequest) {
    for (ResultadoEstad resultadoEstad : resultadoRequest.getEstadisticas()) {
      if (resultadoEstad.getAmarillas() > 0 || resultadoEstad.getRojas() > 0) {
        EstadisticasRequest estadisticasRequest = EstadisticasRequest.builder()
                .idFutbolista(resultadoEstad.getIdFutbolista())
                .amarillas(resultadoEstad.getAmarillas())
                .rojas(resultadoEstad.getRojas())
                .build();

        tarjetasServiceImpl.agregarActualizarTarjetas(estadisticasRequest);
      }
    }
  }

  public void actualizarTablaMvp (ResultadoRequest resultadoRequest) {
    for (ResultadoEstad resultadoEstad : resultadoRequest.getEstadisticas()) {
      if (resultadoEstad.isMvp()) {
        EstadisticasRequest estadisticasRequest = EstadisticasRequest.builder()
                .idFutbolista(resultadoEstad.getIdFutbolista())
                .jornada(resultadoRequest.getJornada())
                .build();

        mvpServiceImpl.agregarActualizarMvp(estadisticasRequest);
      }
    }
  }

  public void actualizarTablaGeneralLocal (ResultadoRequest resultadoRequest, Partido partido) {
    int puntosLocal = 0;
    int victoria = 0;
    int empate = 0;
    int derrota = 0;

    if (resultadoRequest.getGanador().equals("Local")) {
      puntosLocal = 3;
      victoria = 1;
    }
    else if (resultadoRequest.getGanador().equals("Empate")) {
      puntosLocal = 1;
      empate = 1;
    }
    else {
      derrota = 1;
    }

    EstadisticasRequest equipoLocal = EstadisticasRequest.builder()
            .idTabla(resultadoRequest.getIdTablaEquipoLocal())
            .idEquipo(partido.getEquipoLocal().getId())
            .puntos(puntosLocal)
            .victorias(victoria)
            .empates(empate)
            .derrotas(derrota)
            .golesFavor(resultadoRequest.getMarcadorLocal())
            .golesContra(resultadoRequest.getMarcadorVisita())
            .build();

    tablaGeneralServiceImpl.actualizarTablaGeneral(equipoLocal, resultadoRequest.getIdTablaEquipoLocal());
  }

  public void actualizarTablaGeneralVisita (ResultadoRequest resultadoRequest, Partido partido) {
    int puntosVisita = 0;
    int victoria = 0;
    int empate = 0;
    int derrota = 0;

    if (resultadoRequest.getGanador().equals("Visita")) {
      puntosVisita = 3;
      victoria = 1;
    }
    else if (resultadoRequest.getGanador().equals("Empate")) {
      puntosVisita = 1;
      empate = 1;
    }
    else {
      derrota = 1;
    }

    EstadisticasRequest equipoVisita = EstadisticasRequest.builder()
            .idTabla(resultadoRequest.getIdTablaEquipoVisita())
            .idEquipo(partido.getEquipoVisitante().getId())
            .puntos(puntosVisita)
            .victorias(victoria)
            .empates(empate)
            .derrotas(derrota)
            .golesFavor(resultadoRequest.getMarcadorVisita())
            .golesContra(resultadoRequest.getMarcadorLocal())
            .build();

    tablaGeneralServiceImpl.actualizarTablaGeneral(equipoVisita, resultadoRequest.getIdTablaEquipoVisita());
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarResultados () {
    List<Resultado> resultadoList = resultadoRepository.findAll();

    if (!resultadoList.isEmpty()) {
      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Resultados consultados de forma correcta",
              true,
              resultadoList,
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al consultar los resultados",
              "No se encontro ningun registro",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarResultadoById (String id) {
    Optional<Resultado> resultadoOptional = resultadoRepository.findById(id);

    if (resultadoOptional.isPresent()) {
      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Resultados consultados por id de forma correcta",
              true,
              resultadoOptional.stream().collect(Collectors.toList()),
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al consultar los resultados por id",
              "El resultado con id que requiere consultar no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }
}
