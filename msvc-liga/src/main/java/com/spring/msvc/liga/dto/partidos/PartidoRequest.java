package com.spring.msvc.liga.dto.partidos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PartidoRequest {

  @NotNull(message = "La jornada no puede ser NULL")
  private Integer jornada;

  @NotNull(message = "La fecha no puede ser NULL")
  @NotBlank(message = "La fecha no puede estar vacia")
  @Size(max = 20)
  private String fechaPartido;

  @NotNull(message = "El id del equipo local no puede ser NULL")
  @NotBlank(message = "El id del equipo local no puede estar vacio")
  private String idEquipoLocal;

  @NotNull(message = "El id del equipo visitante no puede ser NULL")
  @NotBlank(message = "El id del equipo visitante no puede estar vacia")
  private String idEquipoVisitante;
}
