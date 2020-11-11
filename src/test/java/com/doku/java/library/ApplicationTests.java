package com.doku.java.library;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
        Application.main(new String[]{
                "--spring.main.web-environment=false",
        });
        Assertions.assertTrue(true);
    }

}
