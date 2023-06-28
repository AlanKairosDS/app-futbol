package com.spring.msvc.liga.dto.estadisticas;

import com.spring.msvc.liga.entity.estadisticas.ResultadoEstad;
import com.spring.msvc.liga.entity.partidos.Partido;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ResultadoRequest {

  private String partido;
  private int jornada;
  private int marcadorLocal;
  private int marcadorVisita;
  private String idTablaEquipoLocal;
  private String idTablaEquipoVisita;
  private String ganador;
  private List<ResultadoEstad> estadisticas;
}
