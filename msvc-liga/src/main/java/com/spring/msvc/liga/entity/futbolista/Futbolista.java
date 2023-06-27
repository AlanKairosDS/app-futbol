package com.spring.msvc.liga.entity.futbolista;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "futbolista")
public class Futbolista {

  @Id
  private String id;

  @NotNull(message = "El nombre no puede ser NULL")
  @NotBlank(message = "El nombre no puede estar vacio")
  @Size(max = 100)
  private String nombre;

  @NotNull(message = "La fecha de nacimiento no puede ser NULL")
  @NotBlank(message = "La fecha de nacimiento no puede estar vacia")
  @Size(max = 20)
  private String fechaNacimiento;

  @NotNull(message = "La posicion no puede ser NULL")
  @NotBlank(message = "La posicion no puede estar vacia")
  @Size(max = 50)
  private String posicion;

  private String fechaAlta;

  private String fechaModificacion;
}
