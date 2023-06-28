package com.spring.msvc.liga.services.partidos;

import com.spring.common.tools.entity.RestResponse;
import com.spring.common.tools.service.UtilService;
import com.spring.msvc.liga.dto.partidos.PartidoRequest;
import com.spring.msvc.liga.entity.equipo.Equipo;
import com.spring.msvc.liga.entity.partidos.Partido;
import com.spring.msvc.liga.repositories.equipo.EquipoRepository;
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
public class PartidoServiceImpl implements PartidoService {

  private static final Logger LOGGER = LogManager.getLogger("liga");
  private static final String EXITO = "Proceso ejecutado correctamente";
  private static final String ERROR_CONSULTA = "Ocurrio un error al consultar partido";

  @Autowired
  private PartidosRepository partidosRepository;

  @Autowired
  private EquipoRepository equipoRepository;

  @Autowired
  private UtilService utilService;

  @Override
  public ResponseEntity<RestResponse<Object>> registrarPartido (PartidoRequest partidoRequest) {
    Optional<Equipo> equipoLocal = equipoRepository.findById(partidoRequest.getIdEquipoLocal());

    if (equipoLocal.isPresent()) {
      Optional<Equipo> equipoVisita = equipoRepository.findById(partidoRequest.getIdEquipoVisitante());

      if (equipoVisita.isPresent()) {
        Partido partido = Partido.builder()
                .jornada(partidoRequest.getJornada())
                .fecha(partidoRequest.getFechaPartido())
                .equipoLocal(equipoLocal.get())
                .equipoVisitante(equipoVisita.get())
                .build();

        partidosRepository.save(partido);

        return new UtilService().armarRespuesta(
                HttpStatus.OK.value(),
                EXITO,
                "El partido se registro de forma correcta",
                true,
                null,
                HttpStatus.OK
        );
      }
      else {
        return new UtilService().armarRespuesta(
                HttpStatus.BAD_REQUEST.value(),
                "Ocurrio un error al registrar partido",
                "El equipo visitante no existe",
                true,
                null,
                HttpStatus.BAD_REQUEST
        );
      }
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al registrar partido",
              "El equipo local no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> actualizarPartido (String id, PartidoRequest partidoRequest) {
    Optional<Partido> partidoOptional = partidosRepository.findById(id);
    Optional<Equipo> equipoLocal = equipoRepository.findById(partidoRequest.getIdEquipoLocal());
    Optional<Equipo> equipoVisita = equipoRepository.findById(partidoRequest.getIdEquipoVisitante());

    if (partidoOptional.isPresent()) {
      if (equipoLocal.isPresent() && equipoVisita.isPresent()) {
        Partido partido = partidoOptional.get();

        partido.setJornada(partidoRequest.getJornada());
        partido.setFecha(partidoRequest.getFechaPartido());
        partido.setEquipoLocal(equipoLocal.get());
        partido.setEquipoVisitante(equipoVisita.get());

        partidosRepository.save(partido);

        return new UtilService().armarRespuesta(
                HttpStatus.OK.value(),
                EXITO,
                "El partido se actualizo de forma correcta",
                true,
                null,
                HttpStatus.OK
        );
      }
      else {
        return new UtilService().armarRespuesta(
                HttpStatus.BAD_REQUEST.value(),
                "Ocurrio un error al actualizar partido",
                "Alguno de los equipos que se quieren actualizar no existen",
                true,
                null,
                HttpStatus.BAD_REQUEST
        );
      }
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al actualizar partido",
              "El partido que quieres actualizar no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> eliminarPartido (String id) {
    if (partidosRepository.existsById(id)) {
      partidosRepository.deleteById(id);

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "El partido se elimino de forma correcta",
              true,
              null,
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al eliminar partido",
              "El partido que quieres eliminar no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarPartidos () {
    List<Partido> partidoList = partidosRepository.findAll();

    return new UtilService().armarRespuesta(
            HttpStatus.OK.value(),
            EXITO,
            "Partidos consultados de forma correcta",
            true,
            partidoList,
            HttpStatus.OK
    );
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarPartidoById (String id) {
    if (partidosRepository.existsById(id)) {
      Optional<Partido> optionalPartido = partidosRepository.findById(id);

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Partido consultado de forma correcta",
              true,
              optionalPartido.stream().collect(Collectors.toList()),
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              ERROR_CONSULTA,
              "El partido que quieres consultar no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarPartidoByJornada (String jornada) {
    List<Partido> partidoList = partidosRepository.findByJornada(Integer.parseInt(jornada));

    if (!partidoList.isEmpty()) {
      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se encontraron partidos para la jornada especificada",
              true,
              partidoList,
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              ERROR_CONSULTA,
              "No hay partidos en la jornada especificada",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarPartidoByFecha (String fecha) {
    String fechaFormat = fecha.substring(0, 2) + "/" + fecha.substring(2, 4) + "/" + fecha.substring(4, 8);

    List<Partido> partidoList = partidosRepository.findByFecha(fechaFormat);

    if (!partidoList.isEmpty()) {
      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se encontraron partidos para la fecha especificada",
              true,
              partidoList,
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              ERROR_CONSULTA,
              "No hay partidos en la fecha especificada",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }
}
