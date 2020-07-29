package com.doku.sdk.service;


import com.doku.sdk.client.MandiriClient;
import com.doku.sdk.builder.ClientBuilder;
import com.doku.sdk.client.MandiriSyariahClient;
import com.doku.sdk.dto.payment.request.*;
import com.doku.sdk.dto.payment.response.*;
import com.doku.sdk.pojo.SetupConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;

//Sebagai Bean agar bisa mengakes bean
class PaymentCodeRequestDtoTest {

    @MockBean
    MandiriClient mandiriClientMock;

    @MockBean
    MandiriSyariahClient mandiriSyariahClientMock;

    @MockBean
    ClientBuilder clientBuilder;

    // Data Configuration
    private String clientId = "client-id";
    private String sharedKey = "inirahasia";
    private String invoiceNumber = "JVM-000-11";


    private PaymentCodeRequestDto paymentCodeRequestDto;
    private PaymentCodeResponseDto paymentCodeResponseDtoMock;

    MockWebServer server = new MockWebServer();

    @BeforeEach
    void createVariable() throws IOException {

        // Data Request
        String email = "email.email.com";
        String customerName = "Nama Customer";
        paymentCodeRequestDto = PaymentCodeRequestDto.builder()
                .client(ClientRequestDto.builder()
                        .id(clientId)
                        .build())
                .order(OrderRequestDto.builder()
                        .invoiceNumber(invoiceNumber)
                        .amount(160000L)
                        .build())
                .virtualAccountInfo(VirtualAccountInfoRequestDto.builder()
                        .expiredTime(60)
                        .reusableStatus(false)
                        .info1("Nama Toko")
                        .info2("Kontak Toko")
                        .info3("Greetings Toko")
                        .build())
                .sharedKey(sharedKey)
                .customer(CustomerRequestDto.builder()
                        .name(customerName)
                        .email(email)
                        .build())
                .generateWords()
                .build();

        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setId(clientId);

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setInvoiceNumber(invoiceNumber);

        SecurityResponseDto securityResponseDto = new SecurityResponseDto();
        securityResponseDto.setCheckSum(paymentCodeRequestDto.getSecurity().getCheckSum());

        VirtualAccountInfoResponseDto virtualAccountInfoResponseDto = new VirtualAccountInfoResponseDto();
        virtualAccountInfoResponseDto.setCreatedDate("20200708162202");
        virtualAccountInfoResponseDto.setExpiredDate("20200708172202");
        virtualAccountInfoResponseDto.setHowToPayPage("http://url.how.to.page");
        virtualAccountInfoResponseDto.setHowToPayApi("http://url.how.to.api");
        virtualAccountInfoResponseDto.setVirtualAccountNumber("8123400000000034");

        paymentCodeResponseDtoMock = new PaymentCodeResponseDto();
        paymentCodeResponseDtoMock.setClient(clientResponseDto);
        paymentCodeResponseDtoMock.setOrder(orderResponseDto);
        paymentCodeResponseDtoMock.setSecurity(securityResponseDto);
        paymentCodeResponseDtoMock.setVirtualAccountInfo(virtualAccountInfoResponseDto);

        ObjectMapper objectMapper = new ObjectMapper();

        String jsonResponse = objectMapper.writeValueAsString(paymentCodeResponseDtoMock);

        // Mocking Server
        server.enqueue(new MockResponse().setBody(jsonResponse));
    }


    @Test
    void generatePaycodeMandiri() throws IOException {

        server.start(9393);

        String baseUrl = server.url("/mandiri/").toString();

        SetupConfiguration setupConfiguration = SetupConfiguration.builder()
                .clientId(clientId)
                .merchantName("fascal")
                .sharedKey(sharedKey)
                .serverLocation(baseUrl)
                .build();

        PaymentCodeResponseDto actual = new GeneratePaycodeServices().generateMandiriPaymentCode(setupConfiguration, paymentCodeRequestDto);

        Assertions.assertEquals(actual.getClient().getId(), paymentCodeResponseDtoMock.getClient().getId());
        Assertions.assertEquals(actual.getOrder().getInvoiceNumber(), paymentCodeResponseDtoMock.getOrder().getInvoiceNumber());
        Assertions.assertEquals(actual.getSecurity().getCheckSum(), paymentCodeResponseDtoMock.getSecurity().getCheckSum());
        Assertions.assertEquals(actual.getVirtualAccountInfo().getCreatedDate(), paymentCodeResponseDtoMock.getVirtualAccountInfo().getCreatedDate());
        Assertions.assertEquals(actual.getVirtualAccountInfo().getExpiredDate(), paymentCodeResponseDtoMock.getVirtualAccountInfo().getExpiredDate());
        Assertions.assertEquals(actual.getVirtualAccountInfo().getHowToPayApi(), paymentCodeResponseDtoMock.getVirtualAccountInfo().getHowToPayApi());
        Assertions.assertEquals(actual.getVirtualAccountInfo().getVirtualAccountNumber(), paymentCodeResponseDtoMock.getVirtualAccountInfo().getVirtualAccountNumber());

    }

    @Test
    void generatePaycodeMandiriSyariah() throws IOException {

        server.start(9397);

        String baseUrl = server.url("/mandiriSyariah/").toString();

        SetupConfiguration setupConfiguration = SetupConfiguration.builder()
                .clientId(clientId)
                .merchantName("fascal")
                .sharedKey(sharedKey)
                .serverLocation(baseUrl)
                .build();

        PaymentCodeResponseDto actual = new GeneratePaycodeServices().generateMandiriSyariahPaymentCode(setupConfiguration, paymentCodeRequestDto);

        Assertions.assertEquals(actual.getClient().getId(), paymentCodeResponseDtoMock.getClient().getId());
        Assertions.assertEquals(actual.getOrder().getInvoiceNumber(), paymentCodeResponseDtoMock.getOrder().getInvoiceNumber());
        Assertions.assertEquals(actual.getSecurity().getCheckSum(), paymentCodeResponseDtoMock.getSecurity().getCheckSum());
        Assertions.assertEquals(actual.getVirtualAccountInfo().getCreatedDate(), paymentCodeResponseDtoMock.getVirtualAccountInfo().getCreatedDate());
        Assertions.assertEquals(actual.getVirtualAccountInfo().getExpiredDate(), paymentCodeResponseDtoMock.getVirtualAccountInfo().getExpiredDate());
        Assertions.assertEquals(actual.getVirtualAccountInfo().getHowToPayApi(), paymentCodeResponseDtoMock.getVirtualAccountInfo().getHowToPayApi());
        Assertions.assertEquals(actual.getVirtualAccountInfo().getVirtualAccountNumber(), paymentCodeResponseDtoMock.getVirtualAccountInfo().getVirtualAccountNumber());

    }

}
