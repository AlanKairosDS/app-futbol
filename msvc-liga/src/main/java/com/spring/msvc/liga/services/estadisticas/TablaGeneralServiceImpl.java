package com.spring.msvc.liga.services.estadisticas;

import com.spring.common.tools.entity.RestResponse;
import com.spring.common.tools.service.UtilService;
import com.spring.msvc.liga.dto.estadisticas.EstadisticasRequest;
import com.spring.msvc.liga.entity.equipo.Equipo;
import com.spring.msvc.liga.entity.estadisticas.TablaGeneral;
import com.spring.msvc.liga.repositories.equipo.EquipoRepository;
import com.spring.msvc.liga.repositories.estadisticas.GeneralRepository;
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
public class TablaGeneralServiceImpl implements TablaGeneralService {

  private static final Logger LOGGER = LogManager.getLogger("liga");
  private static final String EXITO = "Proceso ejecutado correctamente";

  @Autowired
  private GeneralRepository generalRepository;

  @Autowired
  private EquipoRepository equipoRepository;

  @Autowired
  private UtilService utilService;


  @Override
  public ResponseEntity<RestResponse<Object>> registrarEquipoTabla (String idEquipo) {
    Optional<Equipo> equipoOptional = equipoRepository.findById(idEquipo);

    if (equipoOptional.isPresent()) {
      TablaGeneral tablaGeneral = TablaGeneral.builder()
              .equipo(equipoOptional.get())
              .puntos(0)
              .victorias(0)
              .empates(0)
              .derrotas(0)
              .golesFavor(0)
              .golesContra(0)
              .diferenciaGoles(0)
              .build();

      generalRepository.save(tablaGeneral);

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Equipo registrado de forma correcta",
              true,
              null,
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al registrar equipo",
              "El equipo que se requiere registrar no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> actualizarTablaGeneral (EstadisticasRequest estadisticasRequest) {
    Optional<TablaGeneral> tablaOptional = generalRepository.findById(estadisticasRequest.getIdTabla());

    if (tablaOptional.isPresent()) {
      TablaGeneral tablaGeneral = tablaOptional.get();

      int golesFavor = tablaOptional.get().getGolesFavor() + estadisticasRequest.getGolesFavor();
      int golesContra = tablaOptional.get().getGolesContra() + estadisticasRequest.getGolesContra();

      tablaGeneral.setPuntos(tablaOptional.get().getPuntos() + estadisticasRequest.getPuntos());
      tablaGeneral.setVictorias(tablaOptional.get().getVictorias() + estadisticasRequest.getVictorias());
      tablaGeneral.setEmpates(tablaOptional.get().getEmpates() + estadisticasRequest.getEmpates());
      tablaGeneral.setDerrotas(tablaOptional.get().getDerrotas() + estadisticasRequest.getDerrotas());
      tablaGeneral.setGolesFavor(golesFavor);
      tablaGeneral.setGolesContra(golesContra);
      tablaGeneral.setDiferenciaGoles(golesFavor - golesContra);

      generalRepository.save(tablaGeneral);

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se actualiza la tabla general de forma correcta",
              true,
              null,
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al consultar el equipo en la tabla general",
              "El equipo que se requiere consultar no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarTablaGeneral () {
    List<TablaGeneral> tablaGeneralList = generalRepository.findAll();

    return new UtilService().armarRespuesta(
            HttpStatus.OK.value(),
            EXITO,
            "Se consulta la tabla general de forma correcta",
            true,
            tablaGeneralList,
            HttpStatus.OK
    );
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarTablaGeneralById (String id) {
    Optional<TablaGeneral> tablaOptional = generalRepository.findById(id);

    if (tablaOptional.isPresent()) {
      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se consulta el equipo en la tabla general de forma correcta",
              true,
              tablaOptional.stream().collect(Collectors.toList()),
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al consultar el equipo en la tabla general",
              "El equipo que se requiere consultar no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }
}
