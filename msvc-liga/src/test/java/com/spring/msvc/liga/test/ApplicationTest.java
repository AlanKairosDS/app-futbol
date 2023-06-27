package com.spring.msvc.liga.test;

import com.spring.msvc.liga.MsvcLigaApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationTest {

  @Test
  public void applicationContextTest () {
    MsvcLigaApplication.main(new String[]{});
  }
}
