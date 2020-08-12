
package com.doku.java.library.dto.cc.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OrderResponsetDto {
    private String invoiceNumber;
    private List<LineItemResponseDto> lineItems = null;
    private Long amount;
    private String createdDate;
    private String sessionId;
}
