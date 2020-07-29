package com.doku.sdk.dto.payment.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClientRequestDto {
    @JsonProperty("id")
    private String id;
}