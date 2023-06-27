package com.spring.msvc.liga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsvcLigaApplication {

  public static void main (String[] args) {
    SpringApplication application = new SpringApplication(MsvcLigaApplication.class);
    application.run(args);
  }
}
