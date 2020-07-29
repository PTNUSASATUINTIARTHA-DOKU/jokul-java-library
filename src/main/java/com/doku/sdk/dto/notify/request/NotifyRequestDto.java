
package com.doku.sdk.dto.notify.request;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class NotifyRequestDto {
    private ClientRequestDto client;
    private OrderRequestDto order;
    private VirtualAccountInfoRequestDto virtualAccountInfo;
    private VirtualAccountPaymentRequestDto virtualAccountPayment;
    private SecurityRequestDto security;
}
