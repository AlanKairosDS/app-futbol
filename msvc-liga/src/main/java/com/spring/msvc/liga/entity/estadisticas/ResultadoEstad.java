package com.spring.msvc.liga.entity.estadisticas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoEstad {

  private String idFutbolista;

  private int goles;

  private int asistencias;

  private int amarillas;

  private int rojas;
}
