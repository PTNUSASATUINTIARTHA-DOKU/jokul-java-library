package com.doku.sdk.pojo;
import lombok.Getter;

@Getter
public enum ServerLocation {
    PROD("https://api.doku.com/"),
    SANDBOX("http://app-sit.doku.com/");
    private String url;

    ServerLocation(String envUrl) {
        this.url = envUrl;
    }
}
