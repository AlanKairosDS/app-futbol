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

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ligaVaronil")
public class LigaVaronil {

  @Id
  private String id;

  private String nombre;

  @DBRef
  private Set<Equipo> equipos = new HashSet<>();

  @DBRef
  private Set<Partido> partidos = new HashSet<>();

  @DBRef
  private TablaGeneral tablaGeneral;

  @DBRef
  private Set<Resultado> resultados = new HashSet<>();

  @DBRef
  private Set<GolesAsistencias> golesAsistencias = new HashSet<>();

  @DBRef
  private Set<Tarjetas> tarjetas = new HashSet<>();

  @DBRef
  private Set<JugadorMvp> jugadorMvp = new HashSet<>();
}
