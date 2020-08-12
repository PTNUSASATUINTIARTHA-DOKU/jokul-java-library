package com.doku.java.library.dto.va.notify;

import com.doku.java.library.dto.va.notify.request.*;
import com.doku.java.library.dto.va.notify.response.*;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NotifyRequestDtoTest {

    @Test
    void buildRequestAndResponseNotify() {

        ClientRequestDto clientRequestDto = new ClientRequestDto();
        clientRequestDto.setId("MCH-DOKU-123");

        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setAmount(10000L);
        orderRequestDto.setInvoiceNumber("INV-DOKU-123");

        VirtualAccountInfoRequestDto virtualAccountInfoRequestDto = new VirtualAccountInfoRequestDto();
        virtualAccountInfoRequestDto.setVirtualAccountNumber("8777890192731");

        SecurityRequestDto securityRequestDto = new SecurityRequestDto();
        securityRequestDto.setCheckSum("securityWords");

        VirtualAccountPaymentRequestDto virtualAccountPaymentRequestDto = new VirtualAccountPaymentRequestDto();
        virtualAccountPaymentRequestDto.setChannelCode("4001");
        virtualAccountPaymentRequestDto.setDate("20200305140902");
        virtualAccountPaymentRequestDto.setReferenceNumber("982842131234");
        virtualAccountPaymentRequestDto.setSystraceNumber("0002313485");

        //Use setter getter for serialize and deserialize
        // In real case, we recieve NotifyRequestDto as a param
        NotifyRequestDto notifyRequestDto = new NotifyRequestDto();
        notifyRequestDto.setClient(clientRequestDto);
        notifyRequestDto.setOrder(orderRequestDto);
        notifyRequestDto.setVirtualAccountInfo(virtualAccountInfoRequestDto);
        notifyRequestDto.setVirtualAccountPayment(virtualAccountPaymentRequestDto);
        notifyRequestDto.setSecurity(securityRequestDto);


        NotifyResponseDto notifyResponseDto = NotifyResponseDto.builder()
                .client(ClientResponseDto.builder()
                        .id(notifyRequestDto.getClient()
                                .getId()).build())
                .order(OrderResponseDto.builder()
                        .amount(notifyRequestDto
                                .getOrder()
                                .getAmount())
                        .invoiceNumber(notifyRequestDto.getOrder()
                                .getInvoiceNumber())
                        .build())
                .virtualAccountInfo(VirtualAccountInfoResponseDto.builder()
                        .virtualAccountNumber(notifyRequestDto
                                .getVirtualAccountInfo()
                                .getVirtualAccountNumber())
                        .build())
                .security(SecurityResponseDto.builder()
                        .checkSum(notifyRequestDto
                                .getSecurity()
                                .getCheckSum())
                        .build())
                .build();

        Assert.assertEquals(notifyResponseDto.getClient().getId(), notifyRequestDto.getClient().getId());
        Assert.assertEquals(notifyResponseDto.getOrder().getAmount(), notifyRequestDto.getOrder().getAmount());
        Assert.assertEquals(notifyResponseDto.getOrder().getInvoiceNumber(), notifyRequestDto.getOrder().getInvoiceNumber());
        Assert.assertEquals(notifyResponseDto.getVirtualAccountInfo().getVirtualAccountNumber(), notifyRequestDto.getVirtualAccountInfo().getVirtualAccountNumber());
        Assert.assertEquals(notifyResponseDto.getSecurity().getCheckSum(), notifyRequestDto.getSecurity().getCheckSum());

        Assertions.assertNotNull(notifyRequestDto.getVirtualAccountPayment().getChannelCode());
        Assertions.assertNotNull(notifyRequestDto.getVirtualAccountPayment().getDate());
        Assertions.assertNotNull(notifyRequestDto.getVirtualAccountPayment().getReferenceNumber());
        Assertions.assertNotNull(notifyRequestDto.getVirtualAccountPayment().getSystraceNumber());

    }
}
