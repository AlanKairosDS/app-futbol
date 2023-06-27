package com.spring.msvc.estadisticas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsvcEstadisticasApplication {

  public static void main (String[] args) {
    SpringApplication application = new SpringApplication(MsvcEstadisticasApplication.class);
    application.run(args);
  }
}
