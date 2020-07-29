package com.doku.sdk.dto.notify.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class NotifyResponseDto {
    private ClientResponseDto client;
    private OrderResponseDto order;
    private VirtualAccountInfoResponseDto virtualAccountInfo;
    private SecurityResponseDto security;
}
