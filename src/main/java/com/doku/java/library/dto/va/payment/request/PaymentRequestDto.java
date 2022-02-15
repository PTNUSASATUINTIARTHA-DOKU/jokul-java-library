
package com.doku.java.library.dto.va.payment.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;

@Builder
@JsonNaming(SnakeCaseStrategy.class)
public class PaymentRequestDto {
    @Getter
    private OrderRequestDto order;
    @Getter
    private VirtualAccountInfoRequestDto virtualAccountInfo;
    @Getter
    private CustomerRequestDto customer;
    @Getter
    private HashMap<String, Object> additionalInfo;

    public static class PaymentRequestDtoBuilder {

        public PaymentRequestDto.PaymentRequestDtoBuilder setAdditionalInfo() {
            this.additionalInfo = new HashMap<>();
            setDefaultAdditional();
            return this;
        }

        private void setDefaultAdditional(){
            Integration integration = new Integration();
            integration.setName("jokul-java-library");
            integration.setVersion("v1.0.3");
            this.additionalInfo.put("integration", integration);
        }

        public PaymentRequestDto.PaymentRequestDtoBuilder setAdditionalInfo(String title, Object object) {
            this.additionalInfo = new HashMap<>();
            this.additionalInfo.put(title, object);
            setDefaultAdditional();
            return this;
        }
    }

}
