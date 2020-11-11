package com.doku.java.library.service.cc;

import com.doku.java.library.dto.cc.request.*;
import com.doku.java.library.dto.cc.response.*;
import com.doku.java.library.pojo.SetupConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class RequestTokenTest {

    @Autowired
    CCService ccService;

    @Test
    void inquiryCC() throws IOException {

        SetupConfiguration setupConfiguration = SetupConfiguration.builder()
                .clientId("1")
                .key("54345435345")
                .environment("http://localhost:9090")
                .build();


        PaymentTokenResponseDto paymentResponseDto = new PaymentTokenResponseDto();
        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setId(setupConfiguration.getClientId());
        paymentResponseDto.setClient(clientResponseDto);

        CCPaymentPageResponseDto ccPaymentPageResponseDto = new CCPaymentPageResponseDto();
        ccPaymentPageResponseDto.setCreatedDate("2020-02-10");
        ccPaymentPageResponseDto.setUrl("http://localhost.com");

        paymentResponseDto.setCreditCardPaymentPage(ccPaymentPageResponseDto);

        OrderResponsetDto orderResponsetDto = new OrderResponsetDto();
        orderResponsetDto.setAmount(20000L);
        orderResponsetDto.setCreatedDate("2020-08-06");
        orderResponsetDto.setInvoiceNumber("INV-123");

        LineItemResponseDto lineItemResponseDto = new LineItemResponseDto();
        lineItemResponseDto.setName("Granola Kering");
        lineItemResponseDto.setPrice(10000L);
        lineItemResponseDto.setQuantity(1);


        List<LineItemResponseDto> lineItemResponseDtoList = new ArrayList<>();
        lineItemResponseDtoList.add(lineItemResponseDto);


        orderResponsetDto.setLineItems(lineItemResponseDtoList);
        paymentResponseDto.setOrder(orderResponsetDto);


        ObjectMapper objectMapper = new ObjectMapper();

        String jsonResponse = objectMapper.writeValueAsString(paymentResponseDto);

        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(jsonResponse));
        server.start(9090);

        List<LineItemRequestDto> lineItemRequestDtoList = new ArrayList<>();
        lineItemRequestDtoList.add(LineItemRequestDto.builder()
                .name("Granola Kering")
                .price(10000L)
                .quantity(1)
                .build());


        PaymentTokenRequestDto paymentRequestDto = PaymentTokenRequestDto.builder()
                .client(ClientRequestDto.builder()
                        .id("1")
                        .build())
                .customer(CustomerRequestDto.builder()
                        .id("508")
                        .name("Fascal si Ganteng")
                        .address("Jl Dipatiukur no 10")
                        .country("ZBW")
                        .email("goodwill@doku.com")
                        .phone("082928201982")
                        .build())
                .order(OrderRequestDto.builder()
                        .amount(10000L)
                        .invoiceNumber("INV-GOOD-123")
                        .callbackUrl("http://localhost:4200/doku-hosted/SU5WFDferd561dfasfasdfaeEW6220204928094958178/redirect")
                        .lineItems(lineItemRequestDtoList)
                        .createdDate("20205028095021022")
                        .sessionId("SU5WFDferd561dfasfasdfaeEW62")
                        .build())
                .sharedKey("1")
                .build();


        PaymentTokenResponseDto actual = ccService.generateToken(setupConfiguration, paymentRequestDto);

        Assert.assertEquals(actual.getClient().getId(), paymentResponseDto.getClient().getId());
        Assert.assertEquals(actual.getCreditCardPaymentPage().getCreatedDate(), paymentResponseDto.getCreditCardPaymentPage().getCreatedDate());
        Assert.assertEquals(actual.getCreditCardPaymentPage().getUrl(), paymentResponseDto.getCreditCardPaymentPage().getUrl());
        Assert.assertEquals(actual.getOrder().getAmount(), paymentResponseDto.getOrder().getAmount());
        Assert.assertEquals(actual.getOrder().getInvoiceNumber(), paymentResponseDto.getOrder().getInvoiceNumber());
        Assert.assertEquals(actual.getOrder().getCreatedDate(), paymentResponseDto.getOrder().getCreatedDate());
        Assert.assertEquals(actual.getOrder().getLineItems().get(0).getName(), paymentResponseDto.getOrder().getLineItems().get(0).getName());
        Assert.assertEquals(actual.getOrder().getLineItems().get(0).getPrice(), paymentResponseDto.getOrder().getLineItems().get(0).getPrice());
        Assert.assertEquals(actual.getOrder().getLineItems().get(0).getQuantity(), paymentResponseDto.getOrder().getLineItems().get(0).getQuantity());
        Assert.assertEquals(actual.getOrder().getSessionId(), paymentResponseDto.getOrder().getSessionId());

        server.shutdown();

    }
}
