//package com.doku.java.library.service.va;
//
//import com.doku.java.library.client.va.DokuVaClient;
//import com.doku.java.library.dto.va.payment.request.CustomerRequestDto;
//import com.doku.java.library.dto.va.payment.request.OrderRequestDto;
//import com.doku.java.library.dto.va.payment.request.PaymentRequestDto;
//import com.doku.java.library.dto.va.payment.request.VirtualAccountInfoRequestDto;
//import com.doku.java.library.dto.va.payment.response.OrderResponseDto;
//import com.doku.java.library.dto.va.payment.response.PaymentResponseDto;
//import com.doku.java.library.dto.va.payment.response.VirtualAccountInfoResponseDto;
//import com.doku.java.library.pojo.SetupConfiguration;
//import com.github.fascalsj.stringmanipulator.RandomCharacter;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Spy;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import static org.mockito.Mockito.mock;
//
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(VaServices.class)
//class GeneratePaymentCodeTest {
//
//    private final String invoiceNumber = "JVM-000-11" + RandomCharacter.getRandomNumber(1, 2000);
//
//
//    private PaymentRequestDto paymentRequestDto;
//    private PaymentResponseDto paymentResponseDto;
//    private SetupConfiguration setupConfiguration;
//
//    @Spy
//    private VaServices myTestClass;
//
//
//    @BeforeEach
//    void createVariable() {
//        String email = "email@email.com";
//        String customerName = "Nama Customer";
//        paymentRequestDto = PaymentRequestDto.builder()
//                .order(OrderRequestDto.builder()
//                        .invoiceNumber(invoiceNumber)
//                        .amount(160000L)
//                        .build())
//                .virtualAccountInfo(VirtualAccountInfoRequestDto.builder()
//                        .expiredTime(60)
//                        .reusableStatus(false)
//                        .info1("Nama Toko")
//                        .info2("Kontak Toko")
//                        .info3("Greetings Toko")
//                        .build())
//                .customer(CustomerRequestDto.builder()
//                        .name(customerName)
//                        .email(email)
//                        .build())
//                .build();
//
//        setupConfiguration = SetupConfiguration.builder()
//                .clientId("MCH-1103200003")
//                .key("TYUI56&*")
//                .environment("http://api-sit.doku.com")
//                .setupServerLocation()
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
//        paymentResponseDto = new PaymentResponseDto();
//        paymentResponseDto.setOrder(orderResponseDto);
//        paymentResponseDto.setVirtualAccountInfo(virtualAccountInfoResponseDto);
//    }
//
//
//}
