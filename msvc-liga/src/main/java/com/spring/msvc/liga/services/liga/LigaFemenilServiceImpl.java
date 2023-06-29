package com.spring.msvc.liga.services.liga;

import com.spring.common.tools.entity.RestResponse;
import com.spring.common.tools.service.UtilService;
import com.spring.msvc.liga.entity.equipo.Equipo;
import com.spring.msvc.liga.entity.estadisticas.GolesAsistencias;
import com.spring.msvc.liga.entity.estadisticas.JugadorMvp;
import com.spring.msvc.liga.entity.estadisticas.Resultado;
import com.spring.msvc.liga.entity.estadisticas.TablaGeneral;
import com.spring.msvc.liga.entity.estadisticas.Tarjetas;
import com.spring.msvc.liga.entity.liga.LigaFemenil;
import com.spring.msvc.liga.entity.partidos.Partido;
import com.spring.msvc.liga.repositories.equipo.EquipoRepository;
import com.spring.msvc.liga.repositories.estadisticas.GeneralRepository;
import com.spring.msvc.liga.repositories.estadisticas.GolesRepository;
import com.spring.msvc.liga.repositories.estadisticas.MvpRepository;
import com.spring.msvc.liga.repositories.estadisticas.ResultadoRepository;
import com.spring.msvc.liga.repositories.estadisticas.TarjetasRepository;
import com.spring.msvc.liga.repositories.liga.LigaFemenilRepository;
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
public class LigaFemenilServiceImpl implements LigaFemenilService {

  private static final Logger LOGGER = LogManager.getLogger("liga");

  private static final String EXITO = "Proceso ejecutado correctamente";

  private static final String ERROR_LIGA = "La liga que estas buscando no existe";

  @Autowired
  private LigaFemenilRepository ligaFemenilRepository;

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
  public ResponseEntity<RestResponse<Object>> nuevaLiga (LigaFemenil ligaFemenil) {
    if (ligaFemenilRepository.existsByNombre(ligaFemenil.getNombre())) {
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
      ligaFemenilRepository.save(ligaFemenil);

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
    List<LigaFemenil> ligaFemenilList = ligaFemenilRepository.findAll();

    return new UtilService().armarRespuesta(
            HttpStatus.OK.value(),
            EXITO,
            "Ligas consultadas de forma correcta",
            true,
            ligaFemenilList,
            HttpStatus.OK
    );
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarLigaById (String id) {
    Optional<LigaFemenil> ligaFemenilOptional = ligaFemenilRepository.findById(id);

    if (ligaFemenilOptional.isPresent()) {
      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Liga consultada por id de forma correcta",
              true,
              ligaFemenilOptional.stream().collect(Collectors.toList()),
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
    Optional<LigaFemenil> ligaFemenilOptional = ligaFemenilRepository.findByNombre(nombre);

    if (ligaFemenilOptional.isPresent()) {
      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se consulto la liga por nombre de forma correcta",
              true,
              ligaFemenilOptional.stream().collect(Collectors.toList()),
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
    Optional<LigaFemenil> ligaFemenilOptional = ligaFemenilRepository.findById(idLiga);

    if (ligaFemenilOptional.isPresent()) {
      Optional<Equipo> equipoOptional = equipoRepository.findById(idEquipo);

      if (equipoOptional.isPresent()) {
        LigaFemenil ligaFemenil = ligaFemenilOptional.get();
        Equipo equipo = equipoOptional.get();

        Set<Equipo> equipoSet = ligaFemenil.getEquipos();
        equipoSet.add(equipo);

        ligaFemenil.setEquipos(equipoSet);

        ligaFemenilRepository.save(ligaFemenil);

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
    Optional<LigaFemenil> ligaFemenilOptional = ligaFemenilRepository.findById(idLiga);

    if (ligaFemenilOptional.isPresent()) {
      Optional<Partido> partidoOptional = partidosRepository.findById(idPartido);

      if (partidoOptional.isPresent()) {
        LigaFemenil ligaFemenil = ligaFemenilOptional.get();
        Partido partido = partidoOptional.get();

        Set<Partido> partidoSet = ligaFemenil.getPartidos();
        partidoSet.add(partido);

        ligaFemenil.setPartidos(partidoSet);

        ligaFemenilRepository.save(ligaFemenil);

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
    Optional<LigaFemenil> ligaFemenilOptional = ligaFemenilRepository.findById(idLiga);

    if (ligaFemenilOptional.isPresent()) {
      Optional<TablaGeneral> tablaGeneralOptional = generalRepository.findById(idTabla);

      if (tablaGeneralOptional.isPresent()) {
        LigaFemenil ligaFemenil = ligaFemenilOptional.get();

        ligaFemenil.setTablaGeneral(tablaGeneralOptional.get());

        ligaFemenilRepository.save(ligaFemenil);

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
    Optional<LigaFemenil> ligaFemenilOptional = ligaFemenilRepository.findById(idLiga);

    if (ligaFemenilOptional.isPresent()) {
      Optional<Resultado> resultadoOptional = resultadoRepository.findById(idResultado);

      if (resultadoOptional.isPresent()) {
        LigaFemenil ligaFemenil = ligaFemenilOptional.get();
        Resultado resultado = resultadoOptional.get();

        Set<Resultado> resultadoSet = ligaFemenil.getResultados();
        resultadoSet.add(resultado);

        ligaFemenil.setResultados(resultadoSet);

        ligaFemenilRepository.save(ligaFemenil);

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
    Optional<LigaFemenil> ligaFemenilOptional = ligaFemenilRepository.findById(idLiga);

    if (ligaFemenilOptional.isPresent()) {
      Optional<GolesAsistencias> golesAsistenciasOptional = golesRepository.findById(idGoles);

      if (golesAsistenciasOptional.isPresent()) {
        LigaFemenil ligaFemenil = ligaFemenilOptional.get();
        GolesAsistencias golesAsistencias = golesAsistenciasOptional.get();

        Set<GolesAsistencias> golesAsistenciasSet = ligaFemenil.getGolesAsistencias();
        golesAsistenciasSet.add(golesAsistencias);

        ligaFemenil.setGolesAsistencias(golesAsistenciasSet);

        ligaFemenilRepository.save(ligaFemenil);

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
    Optional<LigaFemenil> ligaFemenilOptional = ligaFemenilRepository.findById(idLiga);

    if (ligaFemenilOptional.isPresent()) {
      Optional<Tarjetas> tarjetasOptional = tarjetasRepository.findById(idTarjetas);

      if (tarjetasOptional.isPresent()) {
        LigaFemenil ligaFemenil = ligaFemenilOptional.get();
        Tarjetas tarjetas = tarjetasOptional.get();

        Set<Tarjetas> tarjetasSet = ligaFemenil.getTarjetas();
        tarjetasSet.add(tarjetas);

        ligaFemenil.setTarjetas(tarjetasSet);

        ligaFemenilRepository.save(ligaFemenil);

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
    Optional<LigaFemenil> ligaFemenilOptional = ligaFemenilRepository.findById(idLiga);

    if (ligaFemenilOptional.isPresent()) {
      Optional<JugadorMvp> jugadorMvpOptional = mvpRepository.findById(idMvp);

      if (jugadorMvpOptional.isPresent()) {
        LigaFemenil ligaFemenil = ligaFemenilOptional.get();
        JugadorMvp jugadorMvp = jugadorMvpOptional.get();

        Set<JugadorMvp> jugadorMvpSet = ligaFemenil.getJugadorMvp();
        jugadorMvpSet.add(jugadorMvp);

        ligaFemenil.setJugadorMvp(jugadorMvpSet);

        ligaFemenilRepository.save(ligaFemenil);

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
