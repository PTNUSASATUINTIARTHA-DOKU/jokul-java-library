package com.doku.sdk.dto.notify.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class VirtualAccountInfoResponseDto {
    private String virtualAccountNumber;
}
