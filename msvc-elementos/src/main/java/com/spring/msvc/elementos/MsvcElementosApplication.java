package com.spring.msvc.elementos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsvcElementosApplication {

  public static void main (String[] args) {
    SpringApplication application = new SpringApplication(MsvcElementosApplication.class);
    application.run(args);
  }
}

