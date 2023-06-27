package com.spring.msvc.liga.services.equipo;

import com.spring.common.tools.entity.RestResponse;
import com.spring.common.tools.service.UtilService;
import com.spring.msvc.liga.entity.equipo.Equipo;
import com.spring.msvc.liga.entity.futbolista.Futbolista;
import com.spring.msvc.liga.repositories.equipo.EquipoRepository;
import com.spring.msvc.liga.repositories.futbolista.FutbolistaRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EquipoServiceImpl implements EquipoService {

  private static final Logger LOGGER = LogManager.getLogger("elementos");
  private static final String EXITO = "Proceso ejecutado correctamente";

  @Autowired
  private EquipoRepository equipoRepository;

  @Autowired
  private FutbolistaRepository futbolistaRepository;

  @Autowired
  private UtilService utilService;

  @Override
  public ResponseEntity<RestResponse<Object>> registrarEquipo (Equipo equipo) {
    if (equipoRepository.existsByNombre(equipo.getNombre())) {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al registrar equipo",
              "El equipo ingresado ya se encuentra registrado",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
    else {
      equipo.setFechaAlta(utilService.obtenerFechaActual());
      equipo.setFechaModificacion(utilService.obtenerFechaActual());

      equipoRepository.save(equipo);

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Equipo registrado de forma correcta",
              true,
              null,
              HttpStatus.OK
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> actualizarEquipo (String id, Equipo equipo) {
    Optional<Equipo> equipoOptional = equipoRepository.findById(id);

    if (equipoOptional.isPresent()) {
      Equipo equipo1 = equipoOptional.get();

      equipo1.setNombre(equipo.getNombre());
      equipo1.setDescripcion(equipo.getDescripcion());
      equipo1.setEscudo(equipo.getEscudo());
      equipo1.setFechaModificacion(utilService.obtenerFechaActual());

      equipoRepository.save(equipo1);

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Equipo actualizado de forma correcta",
              true,
              null,
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al actualizar equipo",
              "El equipo que se requiere actualizar no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> eliminarEquipo (String id) {
    if (equipoRepository.existsById(id)) {
      equipoRepository.deleteById(id);

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Equipo eliminado de forma correcta",
              true,
              null,
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al eliminar equipo",
              "El equipo que se requiere eliminar no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarEquipos () {
    List<Equipo> equipoList = equipoRepository.findAll();

    return new UtilService().armarRespuesta(
            HttpStatus.OK.value(),
            EXITO,
            "Equipos consultados de forma correcta",
            true,
            equipoList,
            HttpStatus.OK
    );
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarEquipo (String id) {
    if (equipoRepository.existsById(id)) {
      Optional<Equipo> equipoOptional = equipoRepository.findById(id);

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Equipo consultado de forma correcta",
              true,
              equipoOptional.stream().collect(Collectors.toList()),
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al consultar equipo",
              "El equipo que se requiere consultar no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> insertarFutbolista (String idEquipo, String idFutbolista) {
    Optional<Futbolista> futbolistaOptional = futbolistaRepository.findById(idFutbolista);

    if (futbolistaOptional.isPresent()) {
      Optional<Equipo> equipoOptional = equipoRepository.findById(idEquipo);

      if (equipoOptional.isPresent()) {
        Futbolista futbolista = futbolistaOptional.get();
        Equipo equipo = equipoOptional.get();

        Set<Futbolista> futbolistaList = equipo.getFutbolistas();
        futbolistaList.add(futbolista);

        equipo.setFutbolistas(futbolistaList);

        equipoRepository.save(equipo);

        return new UtilService().armarRespuesta(
                HttpStatus.OK.value(),
                EXITO,
                "Se agrego futbolista en el equipo de forma correcta",
                true,
                null,
                HttpStatus.OK
        );
      }
      else {
        return new UtilService().armarRespuesta(
                HttpStatus.BAD_REQUEST.value(),
                "Ocurrio un error al consultar equipo",
                "El equipo que se requiere consultar no existe",
                true,
                null,
                HttpStatus.BAD_REQUEST
        );
      }
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al consultar futbolista",
              "El futbolista que se requiere consultar no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }
}
