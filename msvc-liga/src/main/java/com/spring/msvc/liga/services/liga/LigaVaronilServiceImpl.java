package com.spring.msvc.liga.services.liga;

import com.spring.common.tools.entity.RestResponse;
import com.spring.common.tools.service.UtilService;
import com.spring.msvc.liga.entity.equipo.Equipo;
import com.spring.msvc.liga.entity.estadisticas.GolesAsistencias;
import com.spring.msvc.liga.entity.estadisticas.JugadorMvp;
import com.spring.msvc.liga.entity.estadisticas.Resultado;
import com.spring.msvc.liga.entity.estadisticas.TablaGeneral;
import com.spring.msvc.liga.entity.estadisticas.Tarjetas;
import com.spring.msvc.liga.entity.liga.LigaVaronil;
import com.spring.msvc.liga.entity.partidos.Partido;
import com.spring.msvc.liga.repositories.equipo.EquipoRepository;
import com.spring.msvc.liga.repositories.estadisticas.GeneralRepository;
import com.spring.msvc.liga.repositories.estadisticas.GolesRepository;
import com.spring.msvc.liga.repositories.estadisticas.MvpRepository;
import com.spring.msvc.liga.repositories.estadisticas.ResultadoRepository;
import com.spring.msvc.liga.repositories.estadisticas.TarjetasRepository;
import com.spring.msvc.liga.repositories.liga.LigaVaronilRepository;
import com.spring.msvc.liga.repositories.partidos.PartidosRepository;
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
public class LigaVaronilServiceImpl implements LigaVaronilService {

  private static final Logger LOGGER = LogManager.getLogger("liga");

  private static final String EXITO = "Proceso ejecutado correctamente";

  private static final String ERROR_LIGA = "La liga que estas buscando no existe";

  @Autowired
  private LigaVaronilRepository ligaVaronilRepository;

  @Autowired
  private EquipoRepository equipoRepository;

  @Autowired
  private PartidosRepository partidosRepository;

  @Autowired
  private GeneralRepository generalRepository;

  @Autowired
  private ResultadoRepository resultadoRepository;

  @Autowired
  private GolesRepository golesRepository;

  @Autowired
  private TarjetasRepository tarjetasRepository;

  @Autowired
  private MvpRepository mvpRepository;

  @Autowired
  private UtilService utilService;

  @Override
  public ResponseEntity<RestResponse<Object>> nuevaLiga (LigaVaronil ligaVaronil) {
    if (ligaVaronilRepository.existsByNombre(ligaVaronil.getNombre())) {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al agregar nueva liga",
              "Ya existe una liga con ese nombre",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
    else {
      ligaVaronilRepository.save(ligaVaronil);

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se creo la liga de forma correcta",
              true,
              null,
              HttpStatus.OK
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarLiga () {
    List<LigaVaronil> ligaVaronilList = ligaVaronilRepository.findAll();

    return new UtilService().armarRespuesta(
            HttpStatus.OK.value(),
            EXITO,
            "Ligas consultadas de forma correcta",
            true,
            ligaVaronilList,
            HttpStatus.OK
    );
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarLigaById (String id) {
    Optional<LigaVaronil> ligaVaronilOptional = ligaVaronilRepository.findById(id);

    if (ligaVaronilOptional.isPresent()) {
      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se consulto la liga por id de forma correcta",
              true,
              ligaVaronilOptional.stream().collect(Collectors.toList()),
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al consultar la liga por id",
              "El id de la liga que se requiere consultar no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarLigaByName (String nombre) {
    Optional<LigaVaronil> ligaVaronilOptional = ligaVaronilRepository.findByNombre(nombre);

    if (ligaVaronilOptional.isPresent()) {
      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se consulto la liga por nombre de forma correcta",
              true,
              ligaVaronilOptional.stream().collect(Collectors.toList()),
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              "Ocurrio un error al consultar la liga por nombre",
              "El nombre de la liga que se requiere consultar no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> agregarEquipos (String idLiga, String idEquipo) {
    Optional<LigaVaronil> ligaVaronilOptional = ligaVaronilRepository.findById(idLiga);

    if (ligaVaronilOptional.isPresent()) {
      Optional<Equipo> equipoOptional = equipoRepository.findById(idEquipo);

      if (equipoOptional.isPresent()) {
        LigaVaronil ligaVaronil = ligaVaronilOptional.get();
        Equipo equipo = equipoOptional.get();

        Set<Equipo> equipoSet = ligaVaronil.getEquipos();
        equipoSet.add(equipo);

        ligaVaronil.setEquipos(equipoSet);

        ligaVaronilRepository.save(ligaVaronil);

        return new UtilService().armarRespuesta(
                HttpStatus.OK.value(),
                EXITO,
                "Se agrego equipo en la liga de forma correcta",
                true,
                null,
                HttpStatus.OK
        );
      }
      else {
        return new UtilService().armarRespuesta(
                HttpStatus.BAD_REQUEST.value(),
                "Ocurrio un error al consultar equipo",
                "El equipo que estas buscando no existe",
                true,
                null,
                HttpStatus.BAD_REQUEST
        );
      }
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              ERROR_LIGA,
              ERROR_LIGA,
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> agregarPartidos (String idLiga, String idPartido) {
    Optional<LigaVaronil> ligaVaronilOptional = ligaVaronilRepository.findById(idLiga);

    if (ligaVaronilOptional.isPresent()) {
      Optional<Partido> partidoOptional = partidosRepository.findById(idPartido);

      if (partidoOptional.isPresent()) {
        LigaVaronil ligaVaronil = ligaVaronilOptional.get();
        Partido partido = partidoOptional.get();

        Set<Partido> partidoSet = ligaVaronil.getPartidos();
        partidoSet.add(partido);

        ligaVaronil.setPartidos(partidoSet);

        ligaVaronilRepository.save(ligaVaronil);

        return new UtilService().armarRespuesta(
                HttpStatus.OK.value(),
                EXITO,
                "Se agrego partido en la liga de forma correcta",
                true,
                null,
                HttpStatus.OK
        );
      }
      else {
        return new UtilService().armarRespuesta(
                HttpStatus.BAD_REQUEST.value(),
                "Ocurrio un error al consultar partido",
                "El partido que estas buscando no existe",
                true,
                null,
                HttpStatus.BAD_REQUEST
        );
      }
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              ERROR_LIGA,
              ERROR_LIGA,
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> agregarTablaGeneral (String idLiga, String idTabla) {
    Optional<LigaVaronil> ligaVaronilOptional = ligaVaronilRepository.findById(idLiga);

    if (ligaVaronilOptional.isPresent()) {
      Optional<TablaGeneral> tablaGeneralOptional = generalRepository.findById(idTabla);

      if (tablaGeneralOptional.isPresent()) {
        LigaVaronil ligaVaronil = ligaVaronilOptional.get();

        ligaVaronil.setTablaGeneral(tablaGeneralOptional.get());

        ligaVaronilRepository.save(ligaVaronil);

        return new UtilService().armarRespuesta(
                HttpStatus.OK.value(),
                EXITO,
                "Se agrego tabla general a la liga de forma correcta",
                true,
                null,
                HttpStatus.OK
        );
      }
      else {
        return new UtilService().armarRespuesta(
                HttpStatus.BAD_REQUEST.value(),
                "Ocurrio un error al consultar tabla general",
                "La tabla general que estas buscando no existe",
                true,
                null,
                HttpStatus.BAD_REQUEST
        );
      }
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              ERROR_LIGA,
              ERROR_LIGA,
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> agregarResultados (String idLiga, String idResultado) {
    Optional<LigaVaronil> ligaVaronilOptional = ligaVaronilRepository.findById(idLiga);

    if (ligaVaronilOptional.isPresent()) {
      Optional<Resultado> resultadoOptional = resultadoRepository.findById(idResultado);

      if (resultadoOptional.isPresent()) {
        LigaVaronil ligaVaronil = ligaVaronilOptional.get();
        Resultado resultado = resultadoOptional.get();

        Set<Resultado> resultadoSet = ligaVaronil.getResultados();
        resultadoSet.add(resultado);

        ligaVaronil.setResultados(resultadoSet);

        ligaVaronilRepository.save(ligaVaronil);

        return new UtilService().armarRespuesta(
                HttpStatus.OK.value(),
                EXITO,
                "Se agrego resultado en la liga de forma correcta",
                true,
                null,
                HttpStatus.OK
        );
      }
      else {
        return new UtilService().armarRespuesta(
                HttpStatus.BAD_REQUEST.value(),
                "Ocurrio un error al consultar resultado",
                "El resultado que estas buscando no existe",
                true,
                null,
                HttpStatus.BAD_REQUEST
        );
      }
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              ERROR_LIGA,
              ERROR_LIGA,
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> agregarGolesAsistencias (String idLiga, String idGoles) {
    Optional<LigaVaronil> ligaVaronilOptional = ligaVaronilRepository.findById(idLiga);

    if (ligaVaronilOptional.isPresent()) {
      Optional<GolesAsistencias> golesAsistenciasOptional = golesRepository.findById(idGoles);

      if (golesAsistenciasOptional.isPresent()) {
        LigaVaronil ligaVaronil = ligaVaronilOptional.get();
        GolesAsistencias golesAsistencias = golesAsistenciasOptional.get();

        Set<GolesAsistencias> golesAsistenciasSet = ligaVaronil.getGolesAsistencias();
        golesAsistenciasSet.add(golesAsistencias);

        ligaVaronil.setGolesAsistencias(golesAsistenciasSet);

        ligaVaronilRepository.save(ligaVaronil);

        return new UtilService().armarRespuesta(
                HttpStatus.OK.value(),
                EXITO,
                "Se agrego estadistica de goles y asistencias en la liga de forma correcta",
                true,
                null,
                HttpStatus.OK
        );
      }
      else {
        return new UtilService().armarRespuesta(
                HttpStatus.BAD_REQUEST.value(),
                "Ocurrio un error al consultar estadistica de goles y asistencias",
                "El registro de goles y asistencias que estas buscando no existe",
                true,
                null,
                HttpStatus.BAD_REQUEST
        );
      }
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              ERROR_LIGA,
              ERROR_LIGA,
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> agregarTarjetas (String idLiga, String idTarjetas) {
    Optional<LigaVaronil> ligaVaronilOptional = ligaVaronilRepository.findById(idLiga);

    if (ligaVaronilOptional.isPresent()) {
      Optional<Tarjetas> tarjetasOptional = tarjetasRepository.findById(idTarjetas);

      if (tarjetasOptional.isPresent()) {
        LigaVaronil ligaVaronil = ligaVaronilOptional.get();
        Tarjetas tarjetas = tarjetasOptional.get();

        Set<Tarjetas> tarjetasSet = ligaVaronil.getTarjetas();
        tarjetasSet.add(tarjetas);

        ligaVaronil.setTarjetas(tarjetasSet);

        ligaVaronilRepository.save(ligaVaronil);

        return new UtilService().armarRespuesta(
                HttpStatus.OK.value(),
                EXITO,
                "Se agrego estadistica de tarjetas en la liga de forma correcta",
                true,
                null,
                HttpStatus.OK
        );
      }
      else {
        return new UtilService().armarRespuesta(
                HttpStatus.BAD_REQUEST.value(),
                "Ocurrio un error al consultar estadistica de tarjetas",
                "El registro de tarjetas que estas buscando no existe",
                true,
                null,
                HttpStatus.BAD_REQUEST
        );
      }
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              ERROR_LIGA,
              ERROR_LIGA,
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> agregarMvp (String idLiga, String idMvp) {
    Optional<LigaVaronil> ligaVaronilOptional = ligaVaronilRepository.findById(idLiga);

    if (ligaVaronilOptional.isPresent()) {
      Optional<JugadorMvp> jugadorMvpOptional = mvpRepository.findById(idMvp);

      if (jugadorMvpOptional.isPresent()) {
        LigaVaronil ligaVaronil = ligaVaronilOptional.get();
        JugadorMvp jugadorMvp = jugadorMvpOptional.get();

        Set<JugadorMvp> jugadorMvpSet = ligaVaronil.getJugadorMvp();
        jugadorMvpSet.add(jugadorMvp);

        ligaVaronil.setJugadorMvp(jugadorMvpSet);

        ligaVaronilRepository.save(ligaVaronil);

        return new UtilService().armarRespuesta(
                HttpStatus.OK.value(),
                EXITO,
                "Se agrego estadistica de mvp en la liga de forma correcta",
                true,
                null,
                HttpStatus.OK
        );
      }
      else {
        return new UtilService().armarRespuesta(
                HttpStatus.BAD_REQUEST.value(),
                "Ocurrio un error al consultar estadistica de mvp",
                "El registro de mvp que estas buscando no existe",
                true,
                null,
                HttpStatus.BAD_REQUEST
        );
      }
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              ERROR_LIGA,
              ERROR_LIGA,
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }
}
