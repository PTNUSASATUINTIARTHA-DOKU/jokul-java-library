//package com.doku.java.library.client.va;
//
//import com.doku.java.library.dto.va.payment.request.CustomerRequestDto;
//import com.doku.java.library.dto.va.payment.request.OrderRequestDto;
//import com.doku.java.library.dto.va.payment.request.PaymentRequestDto;
//import com.doku.java.library.dto.va.payment.request.VirtualAccountInfoRequestDto;
//import com.doku.java.library.dto.va.payment.response.OrderResponseDto;
//import com.doku.java.library.dto.va.payment.response.PaymentResponseDto;
//import com.doku.java.library.dto.va.payment.response.VirtualAccountInfoResponseDto;
//import com.doku.java.library.pojo.SetupConfiguration;
//import com.doku.java.library.service.va.VaServices;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.github.fascalsj.stringmanipulator.RandomCharacter;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//@SpringBootTest
//class DokuVaClientTest {
//
//    @Autowired
//    DokuVaClient dokuVaClient;
//
//    private final String path = "/doku-virtual-account/v2/payment-code";
//
//
//    PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
//
//    private final String invoiceNumber = "JVM-000-SDEA" + RandomCharacter.getRandomNumber(1,2000);
//    private PaymentRequestDto paymentRequestDto;
//
//    private SetupConfiguration setupConfiguration;
//
//   @BeforeEach
//    void preDefine() {
//        setupConfiguration = SetupConfiguration.builder()
//                .environment("http://api-sit.doku.com/")
//                .clientId("MCH-1103200003")
//                .key("TYUI56&*")
//                .setupServerLocation()
//                .build();
//
//        paymentRequestDto = PaymentRequestDto.builder()
//                .customer(CustomerRequestDto.builder()
//                        .email("email@email.com")
//                        .name("name")
//                        .build())
//                .virtualAccountInfo(VirtualAccountInfoRequestDto.builder()
//                        .expiredTime(60)
//                        .info1("Info1")
//                        .info2("info2")
//                        .info3("info2")
//                        .reusableStatus(false)
//                        .build())
//                .order(OrderRequestDto.builder()
//                        .amount(10000L)
//                        .invoiceNumber(invoiceNumber)
//                        .build())
//                .build();
//
//        OrderResponseDto orderResponseDto = new OrderResponseDto();
//        orderResponseDto.setInvoiceNumber(invoiceNumber);
//
//        VirtualAccountInfoResponseDto virtualAccountInfoResponseDto = new VirtualAccountInfoResponseDto();
//
//        virtualAccountInfoResponseDto.setCreatedDate("20201012133725");
//        virtualAccountInfoResponseDto.setExpiredDate("20201013133725");
//        virtualAccountInfoResponseDto.setHowToPayApi("http://app-sit.doku.com/how-to-pay/v2/doku-virtual-account/899800000000036");
//        virtualAccountInfoResponseDto.setHowToPayPage("http://api-sit.doku.com/doku-virtual-account/v2/how-to-pay-api/899800000000036");
//        virtualAccountInfoResponseDto.setVirtualAccountNumber("10112317");
//
//        paymentResponseDto.setOrder(orderResponseDto);
//        paymentResponseDto.setVirtualAccountInfo(virtualAccountInfoResponseDto);
//
//    }
//    @Test
//    void testing() throws JsonProcessingException {
//        new DokuVaClient().generateDokuVa(setupConfiguration, paymentRequestDto);
//    }
//
//}