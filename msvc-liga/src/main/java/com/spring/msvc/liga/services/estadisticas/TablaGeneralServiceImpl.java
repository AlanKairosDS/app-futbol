package com.spring.msvc.liga.services.estadisticas;

import com.spring.common.tools.entity.RestResponse;
import com.spring.common.tools.service.UtilService;
import com.spring.msvc.liga.dto.estadisticas.EstadisticasRequest;
import com.spring.msvc.liga.entity.equipo.Equipo;
import com.spring.msvc.liga.entity.estadisticas.EquiposTabla;
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
import java.util.Set;
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
  public ResponseEntity<RestResponse<Object>> nuevaTablaGeneral (TablaGeneral tablaGeneral) {
    generalRepository.save(tablaGeneral);

    return new UtilService().armarRespuesta(
            HttpStatus.OK.value(),
            EXITO,
            "Se creo la tabla general de forma correcta",
            true,
            null,
            HttpStatus.OK
    );
  }

  @Override
  public ResponseEntity<RestResponse<Object>> registrarEquipoTabla (String idTabla, String idEquipo) {
    Optional<TablaGeneral> tablaGeneralOptional = generalRepository.findById(idTabla);

    if (tablaGeneralOptional.isPresent()) {
      Optional<Equipo> equipoOptional = equipoRepository.findById(idEquipo);

      if (equipoOptional.isPresent()) {
        TablaGeneral tablaGeneral = tablaGeneralOptional.get();

        List<EquiposTabla> tablaGeneralEquipos = tablaGeneral.getEquipos();
        EquiposTabla equiposTabla = EquiposTabla.builder()
                .equipo(equipoOptional.get())
                .puntos(0)
                .victorias(0)
                .empates(0)
                .derrotas(0)
                .golesFavor(0)
                .golesContra(0)
                .diferenciaGoles(0)
                .build();

        tablaGeneralEquipos.add(equiposTabla);
        tablaGeneral.setEquipos(tablaGeneralEquipos);

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
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al registrar equipo",
              "La tabla general que estas buscando no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> actualizarTablaGeneral (EstadisticasRequest estadisticasRequest,
          String idTabla) {
    Optional<TablaGeneral> tablaGeneralOptional = generalRepository.findById(idTabla);

    if (tablaGeneralOptional.isPresent()) {
      TablaGeneral tablaGeneral = tablaGeneralOptional.get();
      List<EquiposTabla> equiposTablas = tablaGeneral.getEquipos();

      for (int i = 0; i < equiposTablas.size(); i++) {
        if (equiposTablas.get(i).getEquipo().getId().equals(estadisticasRequest.getIdEquipo())) {
          int golesFavor = equiposTablas.get(i).getGolesFavor() + estadisticasRequest.getGolesFavor();
          int golesContra = equiposTablas.get(i).getGolesContra() + estadisticasRequest.getGolesContra();

          EquiposTabla equiposTabla = new EquiposTabla();

          equiposTabla.setEquipo(equiposTablas.get(i).getEquipo());
          equiposTabla.setPuntos(equiposTablas.get(i).getPuntos() + estadisticasRequest.getPuntos());
          equiposTabla.setVictorias(equiposTablas.get(i).getVictorias() + estadisticasRequest.getVictorias());
          equiposTabla.setEmpates(equiposTablas.get(i).getEmpates() + estadisticasRequest.getEmpates());
          equiposTabla.setDerrotas(equiposTablas.get(i).getDerrotas() + estadisticasRequest.getDerrotas());
          equiposTabla.setGolesFavor(golesFavor);
          equiposTabla.setGolesContra(golesContra);
          equiposTabla.setDiferenciaGoles(golesFavor - golesContra);

          equiposTablas.set(i, equiposTabla);
        }
      }

      tablaGeneral.setEquipos(equiposTablas);

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
              "Ocurrio un error al registrar equipo",
              "La tabla general que quieres actualizar no existe",
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
