package com.doku.sdk.pojo;
import lombok.Getter;

@Getter
public enum ServerLocation {
    SANDBOX("https://sandbox.doku.com/"),
    PROD("https://api.doku.com/");
    private String url;

    ServerLocation(String envUrl) {
        this.url = envUrl;
    }
}
