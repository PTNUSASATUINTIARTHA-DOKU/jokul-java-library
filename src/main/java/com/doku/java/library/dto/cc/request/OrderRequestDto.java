
package com.doku.java.library.dto.cc.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OrderRequestDto {
    private Long amount;
    private String invoiceNumber;
    private String currency;
    private String callbackUrl;
    private List<LineItemRequestDto> lineItems = null;
    private String createdDate;
    private String sessionId;

}
