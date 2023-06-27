package com.spring.msvc.liga.entity.partidos;

import com.spring.msvc.liga.entity.equipo.Equipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Document(collection = "partido")
public class Partido {

  @Id
  private String id;

  @NotNull(message = "La jornada no puede ser NULL")
  private Integer jornada;

  @NotNull(message = "La fecha no puede ser NULL")
  @NotBlank(message = "La fecha no puede estar vacia")
  @Size(max = 20)
  private String fechaPartido;

  @DBRef
  private Equipo equipoLocal;

  @DBRef
  private Equipo equipoVisitante;
}
