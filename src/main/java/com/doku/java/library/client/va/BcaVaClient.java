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
public class BcaVaClient {
    RequestVaClient requestVaClient = new RequestVaClient();
    public ResponseEntity<PaymentResponseDto> generateBcaVa(SetupConfiguration setupConfiguration, PaymentRequestDto paymentRequestDto) throws JsonProcessingException {
        String path = "/bca-virtual-account/v2/payment-code";
        return requestVaClient.requestVa(setupConfiguration,paymentRequestDto,path);
    }
}
