
package com.doku.java.library.dto.cc.request;

import com.doku.java.library.builder.EncryptBuilder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PaymentTokenRequestDto {
    private ClientRequestDto client;
    private CustomerRequestDto customer;
    private OrderRequestDto order;
    private SecurityRequestDto security;

    public static class PaymentTokenRequestDtoBuilder {
        public PaymentTokenRequestDtoBuilder sharedKey(String sharedKey) {
            String words
                    = this.client.getId()
                    + this.order.getInvoiceNumber()
                    + this.order.getAmount()
                    + sharedKey;

            this.security =  SecurityRequestDto.builder().signature(EncryptBuilder.builder()
                    .sha256(words).build()
                    .getSha256())
                    .build();
            return this;
        }
    }


}
