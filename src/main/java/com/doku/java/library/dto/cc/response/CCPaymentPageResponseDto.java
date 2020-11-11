
package com.doku.java.library.dto.cc.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CCPaymentPageResponseDto {
    private String url;
    private String createdDate;

}
