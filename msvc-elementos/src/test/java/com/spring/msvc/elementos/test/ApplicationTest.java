package com.spring.msvc.elementos.test;

import com.spring.msvc.elementos.MsvcElementosApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationTest {

  @Test
  public void applicationContextTest () {
    MsvcElementosApplication.main(new String[]{});
  }
}
