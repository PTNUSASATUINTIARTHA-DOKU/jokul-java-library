package com.doku.sdk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SdkJavaApplicationTests {

    @Test
    void contextLoads() {
        SdkJavaApplication.main(new String[]{
                "--spring.main.web-environment=false",
        });
        Assertions.assertTrue(true);
    }

}
