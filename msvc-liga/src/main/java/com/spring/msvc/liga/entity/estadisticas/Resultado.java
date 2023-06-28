package com.spring.msvc.liga.entity.estadisticas;

import com.spring.msvc.liga.entity.partidos.Partido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "resultado")
public class Resultado {

  @Id
  private String id;

  @DBRef
  private Partido partido;

  private int marcadorLocal;

  private int marcadorVisita;

  private String idEquipoVictoria;

  private String idEquipoDerrota;

  private boolean empate;

  private List<ResultadoEstad> estadisticas;
}
