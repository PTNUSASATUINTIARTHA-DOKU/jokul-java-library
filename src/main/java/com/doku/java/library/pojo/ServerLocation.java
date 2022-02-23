package com.doku.java.library.pojo;
import lombok.Getter;

@Getter
public enum ServerLocation {
    SANDBOX("https://api-sandbox.doku.com"),
    PRODUCTION("https://api.doku.com"),
    SIT("http://api-sit.doku.com");
    private String url;

    ServerLocation(String envUrl) {
        this.url = envUrl;
    }
}
