//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.doku.sdk.dto.payment.request;

import com.doku.sdk.builder.EncryptBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@JsonNaming(SnakeCaseStrategy.class)
public class PaymentCodeRequestDto {
    @Getter
    private ClientRequestDto client;
    @Getter
    private OrderRequestDto order;
    @Getter
    private VirtualAccountInfoRequestDto virtualAccountInfo;
    @Getter
    private CustomerRequestDto customer;
    @JsonIgnore
    private String sharedKey;
    @Getter
    private SecurityRequestDto security;

    PaymentCodeRequestDto(final ClientRequestDto client, final OrderRequestDto order, final VirtualAccountInfoRequestDto virtualAccountInfo, final CustomerRequestDto customer, final String sharedKey, final SecurityRequestDto security) {
        this.client = client;
        this.order = order;
        this.virtualAccountInfo = virtualAccountInfo;
        this.customer = customer;
        this.sharedKey = sharedKey;
        this.security = security;
    }

    public static PaymentCodeRequestDto.PaymentCodeRequestDtoBuilder builder() {
        return new PaymentCodeRequestDto.PaymentCodeRequestDtoBuilder();
    }

    public static class PaymentCodeRequestDtoBuilder {
        private ClientRequestDto client;
        private OrderRequestDto order;
        private VirtualAccountInfoRequestDto virtualAccountInfo;
        private CustomerRequestDto customer;
        private SecurityRequestDto security;
        private String sharedKey;

        PaymentCodeRequestDtoBuilder() {
        }

        public PaymentCodeRequestDto.PaymentCodeRequestDtoBuilder client(final ClientRequestDto client) {
            this.client = client;
            return this;
        }

        public PaymentCodeRequestDto.PaymentCodeRequestDtoBuilder order(final OrderRequestDto order) {
            this.order = order;
            return this;
        }

        public PaymentCodeRequestDto.PaymentCodeRequestDtoBuilder sharedKey(final String sharedKey) {
            this.sharedKey = sharedKey;
            return this;
        }

        public PaymentCodeRequestDto.PaymentCodeRequestDtoBuilder generateWords()  {
            String words = this.client.getId()
                    + this.customer.getEmail()
                    + this.customer.getName()
                    + this.order.getAmount()
                    + this.order.getInvoiceNumber()
                    + this.virtualAccountInfo.getExpiredTime()
                    + this.virtualAccountInfo.getInfo1()
                    + this.virtualAccountInfo.getInfo2()
                    + this.virtualAccountInfo.getInfo3()
                    + this.virtualAccountInfo.getReusableStatus()
                    + this.sharedKey;

            this.security = SecurityRequestDto.builder()
                    .checkSum(EncryptBuilder.builder()
                            .sha256(words).build()
                            .getSha256()).build();
            return this;
        }

        public PaymentCodeRequestDto.PaymentCodeRequestDtoBuilder virtualAccountInfo(final VirtualAccountInfoRequestDto virtualAccountInfo) {
            this.virtualAccountInfo = virtualAccountInfo;
            return this;
        }

        public PaymentCodeRequestDto.PaymentCodeRequestDtoBuilder customer(final CustomerRequestDto customer) {
            this.customer = customer;
            return this;
        }


        public PaymentCodeRequestDto build() {
            return new PaymentCodeRequestDto(this.client, this.order, this.virtualAccountInfo, this.customer, this.sharedKey, this.security);
        }
    }
}
