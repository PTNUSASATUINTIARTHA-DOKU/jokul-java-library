package com.doku.sdk.dto.payment.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PaymentCodeResponseDto {
    private ClientResponseDto client;
    private OrderResponseDto order;
    private VirtualAccountInfoResponseDto virtualAccountInfo;
    private SecurityResponseDto security;
}