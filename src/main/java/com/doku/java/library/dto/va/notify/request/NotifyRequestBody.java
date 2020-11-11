package com.doku.java.library.dto.va.notify.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class NotifyRequestBody {
    private ServiceDto service;
    private AcquirerDto acquirer;
    private ChannelDto channel;
    private OrderRequestDto order;
    private VirtualAccountInfoRequestDto virtualAccountInfo;
    private VirtualAccountPaymentRequestDto virtualAccountPayment;
}

