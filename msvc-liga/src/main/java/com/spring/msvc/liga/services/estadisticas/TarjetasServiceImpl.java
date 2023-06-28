package com.spring.msvc.liga.services.estadisticas;

import com.spring.common.tools.entity.RestResponse;
import com.spring.common.tools.service.UtilService;
import com.spring.msvc.liga.dto.estadisticas.EstadisticasRequest;
import com.spring.msvc.liga.dto.estadisticas.TarjetasResponse;
import com.spring.msvc.liga.entity.estadisticas.Tarjetas;
import com.spring.msvc.liga.entity.futbolista.Futbolista;
import com.spring.msvc.liga.repositories.estadisticas.TarjetasRepository;
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
public class TarjetasServiceImpl implements TarjetasService {

  private static final Logger LOGGER = LogManager.getLogger("liga");
  private static final String EXITO = "Proceso ejecutado correctamente";

  @Autowired
  private TarjetasRepository tarjetasRepository;

  @Autowired
  private FutbolistaRepository futbolistaRepository;

  @Autowired
  private UtilService utilService;


  @Override
  public ResponseEntity<RestResponse<Object>> agregarActualizarTarjetas (EstadisticasRequest estadisticasRequest) {
    Optional<Tarjetas> tarjetasOptional = tarjetasRepository.findByFutbolista(estadisticasRequest.getIdFutbolista());

    if (tarjetasOptional.isPresent()) {
      Tarjetas actualizaTarjetas = tarjetasOptional.get();

      actualizaTarjetas.setAmarillas(tarjetasOptional.get().getAmarillas() + estadisticasRequest.getAmarillas());
      actualizaTarjetas.setRojas(tarjetasOptional.get().getRojas() + estadisticasRequest.getRojas());

      tarjetasRepository.save(actualizaTarjetas);

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se actualizaron las tarjetas del futbolista de forma correcta",
              true,
              null,
              HttpStatus.OK
      );
    }
    else {
      Tarjetas registraTarjetas = Tarjetas.builder()
              .futbolista(estadisticasRequest.getIdFutbolista())
              .amarillas(estadisticasRequest.getAmarillas())
              .rojas(estadisticasRequest.getRojas())
              .build();

      tarjetasRepository.save(registraTarjetas);

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se registraron las tarjetas del futbolista de forma correcta",
              true,
              null,
              HttpStatus.OK
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarTarjetas () {
    List<Tarjetas> tarjetasList = tarjetasRepository.findAll();
    List<TarjetasResponse> tarjetasFutbolistaList = new ArrayList<>();

    if (!tarjetasList.isEmpty()) {
      for (Tarjetas tarjeta : tarjetasList) {
        Optional<Futbolista> futbolistaOptional = futbolistaRepository.findById(tarjeta.getFutbolista());

        TarjetasResponse tarjetasResponse = TarjetasResponse.builder()
                .id(tarjeta.getId())
                .futbolista(futbolistaOptional.get())
                .amarillas(tarjeta.getAmarillas())
                .rojas(tarjeta.getRojas())
                .build();

        tarjetasFutbolistaList.add(tarjetasResponse);
      }

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se consulta la tabla de tarjetas de forma correcta",
              true,
              tarjetasFutbolistaList,
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al consultar la tabla de tarjetas",
              "No se encontro ningun registro",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarTarjetasById (String id) {
    Optional<Tarjetas> tarjetasOptional = tarjetasRepository.findById(id);

    if (tarjetasOptional.isPresent()) {
      Optional<Futbolista> futbolistaOptional = futbolistaRepository.findById(tarjetasOptional.get().getFutbolista());

      TarjetasResponse tarjetasResponse = TarjetasResponse.builder()
              .id(tarjetasOptional.get().getId())
              .futbolista(futbolistaOptional.get())
              .amarillas(tarjetasOptional.get().getAmarillas())
              .rojas(tarjetasOptional.get().getRojas())
              .build();

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se consulta la tabla de tarjetas por id de forma correcta",
              true,
              tarjetasResponse,
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al consultar la tabla de tarjetas por id",
              "El id que se requiere consultar no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarTarjetasByFutbolista (String id) {
    Optional<Tarjetas> tarjetasOptional = tarjetasRepository.findByFutbolista(id);

    if (tarjetasOptional.isPresent()) {
      Optional<Futbolista> futbolistaOptional = futbolistaRepository.findById(id);

      TarjetasResponse tarjetasResponse = TarjetasResponse.builder()
              .id(tarjetasOptional.get().getId())
              .futbolista(futbolistaOptional.get())
              .amarillas(tarjetasOptional.get().getAmarillas())
              .rojas(tarjetasOptional.get().getRojas())
              .build();

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se consulta la tabla de tarjetas por futbolista de forma correcta",
              true,
              tarjetasResponse,
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al consultar la tabla de tarjetas por futbolista",
              "El futbolista que se requiere consultar no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }
}
