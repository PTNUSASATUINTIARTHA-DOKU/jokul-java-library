package com.doku.sdk.pojo;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SetupConfiguration {
    private String merchantName;
    private String clientId;
    private String sharedKey;
    private String serverLocation;
}
