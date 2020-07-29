
package com.doku.sdk.dto.notify.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class VirtualAccountPaymentRequestDto {
    private String date;
    private String systraceNumber;
    private String referenceNumber;
    private String channelCode;
}
