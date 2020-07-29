package com.doku.sdk.dto.payment.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Builder
@Getter
public class VirtualAccountInfoRequestDto {
    private Integer expiredTime;
    private Boolean reusableStatus;
    private String info1;
    private String info2;
    private String info3;
}