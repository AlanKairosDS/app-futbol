package com.spring.msvc.liga.entity.estadisticas;

import com.spring.msvc.liga.entity.futbolista.Futbolista;
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
@Document(collection = "tarjetas")
public class Tarjetas {

  @Id
  private String id;

  private String futbolista;

  private int amarillas;

  private int rojas;
}
