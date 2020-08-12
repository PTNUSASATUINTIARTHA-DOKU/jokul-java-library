package com.doku.java.library.pojo;
import lombok.Getter;

@Getter
public enum ServerLocation {
    SANDBOX("https://sandbox.doku.com/"),
    PRODUCTION("https://api.doku.com/"),
    SIT("http://app-sit.doku.com/");
    private String url;

    ServerLocation(String envUrl) {
        this.url = envUrl;
    }
}
