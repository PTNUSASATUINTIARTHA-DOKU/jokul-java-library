package com.doku.java.library.service.va;


import com.doku.java.library.builder.ClientBuilder;
import com.doku.java.library.client.va.MandiriClient;
import com.doku.java.library.client.va.MandiriSyariahClient;
import com.doku.java.library.dto.va.payment.request.*;
import com.doku.java.library.dto.va.payment.response.*;
import com.doku.java.library.pojo.SetupConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
@SpringBootTest
class GeneratePaymentCodeTest {

    @MockBean
    MandiriClient mandiriClientMock;

    @MockBean
    MandiriSyariahClient mandiriSyariahClientMock;

    @MockBean
    ClientBuilder clientBuilder;

    @Autowired
    VaServices vaServices;


    // Data Configuration
    private final String clientId = "client-id";
    private final String sharedKey = "inirahasia";
    private final String invoiceNumber = "JVM-000-11";
    private final String errorMessage = "Merchant Not Found";
    private final Integer errorStatusCode = 404;

    String jsonResponse = "";


    private PaymentCodeRequestDto paymentCodeRequestDto;
    private PaymentCodeResponseDto paymentCodeResponseDtoMock;



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

        jsonResponse = objectMapper.writeValueAsString(paymentCodeResponseDtoMock);

    }


    @Test
    void generatePaycodeMandiri() throws IOException {
        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(jsonResponse));
        server.start(9091);

        String baseUrl = server.url("/mandiri/").toString();

        SetupConfiguration setupConfiguration = SetupConfiguration.builder()
                .clientId(clientId)
                .merchantName("fascal")
                .sharedKey(sharedKey)
                .environment(baseUrl)
                .setupServerLocation()
                .build();

        PaymentCodeResponseDto actual = new VaServices().generateMandiriVa(setupConfiguration, paymentCodeRequestDto);

        Assertions.assertEquals(actual.getClient().getId(), paymentCodeResponseDtoMock.getClient().getId());
        Assertions.assertEquals(actual.getOrder().getInvoiceNumber(), paymentCodeResponseDtoMock.getOrder().getInvoiceNumber());
        Assertions.assertEquals(actual.getSecurity().getCheckSum(), paymentCodeResponseDtoMock.getSecurity().getCheckSum());
        Assertions.assertEquals(actual.getVirtualAccountInfo().getCreatedDate(), paymentCodeResponseDtoMock.getVirtualAccountInfo().getCreatedDate());
        Assertions.assertEquals(actual.getVirtualAccountInfo().getExpiredDate(), paymentCodeResponseDtoMock.getVirtualAccountInfo().getExpiredDate());
        Assertions.assertEquals(actual.getVirtualAccountInfo().getHowToPayApi(), paymentCodeResponseDtoMock.getVirtualAccountInfo().getHowToPayApi());
        Assertions.assertEquals(actual.getVirtualAccountInfo().getVirtualAccountNumber(), paymentCodeResponseDtoMock.getVirtualAccountInfo().getVirtualAccountNumber());
        server.shutdown();

    }



    @Test
    void generatePaycodeMandiriSyariah() throws IOException {

        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(jsonResponse));
        server.start(9091);

        String baseUrl = server.url("/mandiriSyariah/").toString();

        SetupConfiguration setupConfiguration = SetupConfiguration.builder()
                .clientId(clientId)
                .merchantName("fascal")
                .sharedKey(sharedKey)
                .environment(baseUrl)
                .setupServerLocation()
                .build();

        PaymentCodeResponseDto actual = new VaServices().generateMandiriSyariahVa(setupConfiguration, paymentCodeRequestDto);

        Assertions.assertEquals(actual.getClient().getId(), paymentCodeResponseDtoMock.getClient().getId());
        Assertions.assertEquals(actual.getOrder().getInvoiceNumber(), paymentCodeResponseDtoMock.getOrder().getInvoiceNumber());
        Assertions.assertEquals(actual.getSecurity().getCheckSum(), paymentCodeResponseDtoMock.getSecurity().getCheckSum());
        Assertions.assertEquals(actual.getVirtualAccountInfo().getCreatedDate(), paymentCodeResponseDtoMock.getVirtualAccountInfo().getCreatedDate());
        Assertions.assertEquals(actual.getVirtualAccountInfo().getExpiredDate(), paymentCodeResponseDtoMock.getVirtualAccountInfo().getExpiredDate());
        Assertions.assertEquals(actual.getVirtualAccountInfo().getHowToPayApi(), paymentCodeResponseDtoMock.getVirtualAccountInfo().getHowToPayApi());
        Assertions.assertEquals(actual.getVirtualAccountInfo().getVirtualAccountNumber(), paymentCodeResponseDtoMock.getVirtualAccountInfo().getVirtualAccountNumber());


        server.shutdown();

    }


    @Test
    void generatePaycodeMandiriFailed() throws IOException {
        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody("{\n" +
                "    \"client\": {\n" +
                "        \"id\": \"" +
                clientId +
                "\"" +
                "    },\n" +
                "    \"order\": {\n" +
                "        \"invoice_number\": \"" +
                invoiceNumber+
                "\"\n" +
                "    },\n" +
                "    \"error\": {\n" +
                "        \"message\": \"" +
                errorMessage +
                "\"\n" +
                "    }\n" +
                "}").setResponseCode(errorStatusCode));
        server.start(9091);

        String baseUrl = server.url("/mandiriSyariah/").toString();

        SetupConfiguration setupConfiguration = SetupConfiguration.builder()
                .clientId(clientId)
                .merchantName("fascal")
                .sharedKey(sharedKey)
                .environment(baseUrl)
                .setupServerLocation()
                .build();

        PaymentCodeResponseDto actual = new VaServices().generateMandiriVa(setupConfiguration, paymentCodeRequestDto);

        Assertions.assertEquals(actual.getClient().getId(), paymentCodeResponseDtoMock.getClient().getId());
        Assertions.assertEquals(actual.getError().getMessage(), errorMessage);
        Assertions.assertEquals(actual.getOrder().getInvoiceNumber(), invoiceNumber);
        Assertions.assertEquals(actual.getError().getStatusCode(), errorStatusCode);

        server.shutdown();

    }

    @Test
    void generatePaycodeMandiriSyariahFailed() throws IOException {
        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody("{\n" +
                "    \"client\": {\n" +
                "        \"id\": \"" +
                clientId +
                "\"" +
                "    },\n" +
                "    \"order\": {\n" +
                "        \"invoice_number\": \"" +
                invoiceNumber+
                "\"\n" +
                "    },\n" +
                "    \"error\": {\n" +
                "        \"message\": \"" +
                errorMessage +
                "\"\n" +
                "    }\n" +
                "}").setResponseCode(errorStatusCode));
        server.start(9091);

        String baseUrl = server.url("/mandiriSyariah/").toString();

        SetupConfiguration setupConfiguration = SetupConfiguration.builder()
                .clientId(clientId)
                .merchantName("fascal")
                .sharedKey(sharedKey)
                .environment(baseUrl)
                .setupServerLocation()
                .build();

        PaymentCodeResponseDto actual = new VaServices().generateMandiriSyariahVa(setupConfiguration, paymentCodeRequestDto);

        Assertions.assertEquals(actual.getClient().getId(), paymentCodeResponseDtoMock.getClient().getId());
        Assertions.assertEquals(actual.getError().getMessage(), errorMessage);
        Assertions.assertEquals(actual.getOrder().getInvoiceNumber(), invoiceNumber);
        Assertions.assertEquals(actual.getError().getStatusCode(), errorStatusCode);
        server.shutdown();

    }



}
