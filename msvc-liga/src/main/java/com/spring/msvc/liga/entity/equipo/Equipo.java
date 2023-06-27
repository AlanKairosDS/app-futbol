package com.spring.msvc.liga.entity.equipo;

import com.spring.msvc.liga.entity.futbolista.Futbolista;
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

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "equipo")
public class Equipo {

  @Id
  private String id;

  @NotNull(message = "El nombre no puede ser NULL")
  @NotBlank(message = "El nombre no puede estar vacio")
  @Size(max = 50)
  private String nombre;

  private String descripcion;

  private String escudo;

  private String fechaAlta;

  private String fechaModificacion;

  @DBRef
  private Set<Futbolista> futbolistas = new HashSet<>();
}
