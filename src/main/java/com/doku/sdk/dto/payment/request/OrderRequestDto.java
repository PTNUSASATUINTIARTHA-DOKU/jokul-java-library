package com.doku.sdk.dto.payment.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Builder
@Getter
public class OrderRequestDto {
    private String invoiceNumber;
    private Long amount;
}