package com.doku.java.library.pojo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class SetupConfigurationTest {

    private final String merchantName = "Merchantny Doku";
    private final String sharedKey = "SharedKeyNyaDoku";
    private final String clientId = "ClientIdNyaDoku";
    
    @Test
    void setupConfigurationPROD() {
        String environment = "sandbox";

        SetupConfiguration setupConfiguration = SetupConfiguration
                .builder()
                .clientId(clientId)
                .key(sharedKey)
                .environment(environment)
                .setupServerLocation()
                .build();

        Assertions.assertEquals(sharedKey, setupConfiguration.getKey());
        Assertions.assertEquals(clientId, setupConfiguration.getClientId());
        Assertions.assertEquals(setupConfiguration.getEnvironment(), environment);
        Assertions.assertEquals(ServerLocation.SANDBOX.getUrl(), setupConfiguration.getServerLocation());
    }

    @Test
    void setupConfigurationSANDBOX() {
        String environment = "production";
        SetupConfiguration setupConfiguration = SetupConfiguration
                .builder()
                .clientId(clientId)
                .key(sharedKey)
                .environment(environment)
                .setupServerLocation()
                .build();

        Assertions.assertEquals(sharedKey, setupConfiguration.getKey());
        Assertions.assertEquals(clientId, setupConfiguration.getClientId());
        Assertions.assertEquals(setupConfiguration.getEnvironment(), environment);
        Assertions.assertEquals(ServerLocation.PRODUCTION.getUrl(), setupConfiguration.getServerLocation());
    }
}