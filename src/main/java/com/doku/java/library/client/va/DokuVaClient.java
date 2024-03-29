package com.doku.java.library.client.va;

import com.doku.java.library.dto.va.payment.request.PaymentRequestDto;
import com.doku.java.library.dto.va.payment.response.PaymentResponseDto;
import com.doku.java.library.pojo.SetupConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class DokuVaClient {
    RequestVaClient requestVaClient = new RequestVaClient();
    public ResponseEntity<PaymentResponseDto> generateDokuVa(SetupConfiguration setupConfiguration, PaymentRequestDto paymentRequestDto) throws JsonProcessingException {
        String path = "/doku-virtual-account/v2/payment-code";
        return requestVaClient.requestVa(setupConfiguration,paymentRequestDto,path);
    }
}
