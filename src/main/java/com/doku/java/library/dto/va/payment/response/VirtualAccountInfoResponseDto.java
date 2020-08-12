package com.doku.java.library.dto.va.payment.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Setter
@Getter
public class VirtualAccountInfoResponseDto {
    private String virtualAccountNumber;
    private String howToPayPage;
    private String howToPayApi;
    private String createdDate;
    private String expiredDate;
}