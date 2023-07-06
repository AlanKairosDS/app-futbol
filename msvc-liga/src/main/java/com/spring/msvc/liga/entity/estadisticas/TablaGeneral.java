package com.spring.msvc.liga.entity.estadisticas;

import com.spring.msvc.liga.entity.equipo.Equipo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tablaGeneral")
public class TablaGeneral {

  @Id
  private String id;

  private String nombre;

  private List<EquiposTabla> equipos = new ArrayList<>();
}
