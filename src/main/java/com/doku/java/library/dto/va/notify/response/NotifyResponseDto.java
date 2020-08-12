package com.doku.java.library.dto.va.notify.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;

@Builder
@Generated
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class NotifyResponseDto {
    private ClientResponseDto client;
    private OrderResponseDto order;
    private VirtualAccountInfoResponseDto virtualAccountInfo;
    private SecurityResponseDto security;
}
