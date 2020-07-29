package com.doku.sdk.pojo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class SetupConfigurationTest {

    private final String merchantName = "Merchantny Doku";
    private final String sharedKey = "SharedKeyNyaDoku";
    private final String clientId = "ClientIdNyaDoku";
    
    @Test
    void setupConfigurationPROD() {
        SetupConfiguration setupConfiguration = SetupConfiguration
                .builder()
                .merchantName(merchantName)
                .clientId(clientId)
                .sharedKey(sharedKey)
                .serverLocation(ServerLocation.PROD.getUrl())
                .build();


        Assertions.assertEquals(merchantName, setupConfiguration.getMerchantName());
        Assertions.assertEquals(sharedKey, setupConfiguration.getSharedKey());
        Assertions.assertEquals(clientId, setupConfiguration.getClientId());
        Assertions.assertEquals(ServerLocation.PROD.getUrl(), setupConfiguration.getServerLocation());
    }

    @Test
    void setupConfigurationSANDBOX() {
        SetupConfiguration setupConfiguration = SetupConfiguration
                .builder()
                .merchantName(merchantName)
                .clientId(clientId)
                .sharedKey(sharedKey)
                .serverLocation(ServerLocation.SANDBOX.getUrl())
                .build();

        Assertions.assertEquals(merchantName, setupConfiguration.getMerchantName());
        Assertions.assertEquals(sharedKey, setupConfiguration.getSharedKey());
        Assertions.assertEquals(clientId, setupConfiguration.getClientId());
        Assertions.assertEquals(ServerLocation.SANDBOX.getUrl(), setupConfiguration.getServerLocation());
    }

}