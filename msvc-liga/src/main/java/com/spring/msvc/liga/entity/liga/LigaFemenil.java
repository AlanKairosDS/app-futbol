package com.spring.msvc.liga.entity.liga;

import com.spring.msvc.liga.entity.equipo.Equipo;
import com.spring.msvc.liga.entity.estadisticas.GolesAsistencias;
import com.spring.msvc.liga.entity.estadisticas.JugadorMvp;
import com.spring.msvc.liga.entity.estadisticas.Resultado;
import com.spring.msvc.liga.entity.estadisticas.TablaGeneral;
import com.spring.msvc.liga.entity.estadisticas.Tarjetas;
import com.spring.msvc.liga.entity.partidos.Partido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ligaFemenil")
public class LigaFemenil {

  @Id
  private String id;

  private String nombre;

  @DBRef
  private List<Equipo> equipos = new ArrayList<>();

  @DBRef
  private List<Partido> partidos = new ArrayList<>();

  @DBRef
  private List<TablaGeneral> tablaGeneral = new ArrayList<>();

  @DBRef
  private List<Resultado> resultados = new ArrayList<>();

  @DBRef
  private List<GolesAsistencias> golesAsistencias = new ArrayList<>();

  @DBRef
  private List<Tarjetas> tarjetas = new ArrayList<>();

  @DBRef
  private List<JugadorMvp> jugadorMvp = new ArrayList<>();
}
