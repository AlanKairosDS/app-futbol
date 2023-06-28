package com.spring.msvc.liga.services.estadisticas;

import com.spring.common.tools.entity.RestResponse;
import com.spring.common.tools.service.UtilService;
import com.spring.msvc.liga.entity.estadisticas.Resultado;
import com.spring.msvc.liga.repositories.estadisticas.ResultadoRepository;
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
  public ResponseEntity<RestResponse<Object>> registrarResultado (Resultado resultado) {
    return null;
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
