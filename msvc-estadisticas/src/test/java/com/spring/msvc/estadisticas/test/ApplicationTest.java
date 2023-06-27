package com.spring.msvc.estadisticas.test;

import com.spring.msvc.estadisticas.MsvcEstadisticasApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationTest {

  @Test
  public void applicationContextTest () {
    MsvcEstadisticasApplication.main(new String[]{});
  }
}
