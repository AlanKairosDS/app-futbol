package com.spring.msvc.liga.dto.estadisticas;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EstadisticasRequest {

  private String idFutbolista;
  private String idEquipo;
  private String idPartido;
  private String idResultado;
  private String idTabla;
  private int goles;
  private int asitencias;
  private int amarillas;
  private int rojas;
  private int jornada;
  private int mvp;
  private int puntos;
  private int victorias;
  private int empates;
  private int derrotas;
  private int golesFavor;
  private int golesContra;
  private int diferenciaGoles;
}
