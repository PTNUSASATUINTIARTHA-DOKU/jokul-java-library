package com.doku.java.library.dto.va.notify.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotifyRequestHeader {

    @JsonProperty(value = "Client-Id")
    String clientId;
    @JsonProperty(value = "Signature")
    String signature;
    @JsonProperty(value = "Request-Id")
    String requestId;
    @JsonProperty(value = "Request-Timestamp")
    String requestTimeStamp;

}
