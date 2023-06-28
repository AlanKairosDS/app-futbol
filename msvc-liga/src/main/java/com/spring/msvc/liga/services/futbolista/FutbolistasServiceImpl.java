package com.spring.msvc.liga.services.futbolista;

import com.spring.common.tools.entity.RestResponse;
import com.spring.common.tools.service.UtilService;
import com.spring.msvc.liga.entity.futbolista.Futbolista;
import com.spring.msvc.liga.repositories.futbolista.FutbolistaRepository;
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
public class FutbolistasServiceImpl implements FutbolistaService {

  private static final Logger LOGGER = LogManager.getLogger("liga");
  private static final String EXITO = "Proceso ejecutado correctamente";

  @Autowired
  private FutbolistaRepository futbolistaRepository;

  @Autowired
  private UtilService utilService;

  @Override
  public ResponseEntity<RestResponse<Object>> registrarFutbolista (Futbolista futbolista) {
    if (futbolistaRepository.existsByNombre(futbolista.getNombre())) {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al registrar futbolista",
              "El futbolista ingresado ya se encuentra registrado",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
    else {
      futbolista.setFechaAlta(utilService.obtenerFechaActual());
      futbolista.setFechaModificacion(utilService.obtenerFechaActual());

      futbolistaRepository.save(futbolista);

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Futbolista registrado de forma correcta",
              true,
              null,
              HttpStatus.OK
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> actualizarFutbolista (String id, Futbolista futbolista) {
    Optional<Futbolista> futbolistaOptional = futbolistaRepository.findById(id);

    if (futbolistaOptional.isPresent()) {
      Futbolista futbolista1 = futbolistaOptional.get();

      futbolista1.setNombre(futbolista.getNombre());
      futbolista1.setPosicion(futbolista.getPosicion());
      futbolista1.setFechaModificacion(utilService.obtenerFechaActual());

      futbolistaRepository.save(futbolista1);

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Futbolista actualizado de forma correcta",
              true,
              null,
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al actualizar futbolista",
              "El futbolista que se requiere actualizar no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> eliminarFutbolista (String id) {
    if (futbolistaRepository.existsById(id)) {
      futbolistaRepository.deleteById(id);

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Futbolista eliminado de forma correcta",
              true,
              null,
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al eliminar futbolista",
              "El futbolista que se requiere eliminar no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarFutbolistas () {
    List<Futbolista> futbolistaList = futbolistaRepository.findAll();

    return new UtilService().armarRespuesta(
            HttpStatus.OK.value(),
            EXITO,
            "Futbolistas consultados de forma correcta",
            true,
            futbolistaList,
            HttpStatus.OK
    );
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarFutbolista (String id) {
    if (futbolistaRepository.existsById(id)) {
      Optional<Futbolista> futbolistaOptional = futbolistaRepository.findById(id);

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Futbolista consultado de forma correcta",
              true,
              futbolistaOptional.stream().collect(Collectors.toList()),
              HttpStatus.OK
      );
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
