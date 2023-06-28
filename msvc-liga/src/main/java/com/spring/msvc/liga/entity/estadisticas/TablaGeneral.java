package com.spring.msvc.liga.entity.estadisticas;

import com.spring.msvc.liga.entity.equipo.Equipo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tablaGeneral")
public class TablaGeneral {

  @Id
  private String id;

  @DBRef
  private Equipo equipo;

  private int puntos;

  private int victorias;

  private int empates;

  private int derrotas;

  private int golesFavor;

  private int golesContra;

  private int diferenciaGoles;
}
