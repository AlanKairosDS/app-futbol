package com.spring.msvc.liga.dto.estadisticas;

import com.spring.msvc.liga.entity.futbolista.Futbolista;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TarjetasResponse {

  private String id;
  private Futbolista futbolista;
  private int amarillas;
  private int rojas;
}
