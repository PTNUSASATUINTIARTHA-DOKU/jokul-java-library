package com.doku.java.library.dto.va.payment.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PaymentResponseDto {
    private OrderResponseDto order;
    private VirtualAccountInfoResponseDto virtualAccountInfo;
}