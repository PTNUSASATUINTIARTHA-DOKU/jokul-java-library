package com.doku.java.library.client.va;

import com.doku.java.library.dto.va.payment.request.PaymentRequestDto;
import com.doku.java.library.dto.va.payment.response.PaymentResponseDto;
import com.doku.java.library.pojo.SetupConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PermataVaClient {
    RequestVaClient requestVaClient = new RequestVaClient();
    public ResponseEntity<PaymentResponseDto> generatePrmataVa(SetupConfiguration setupConfiguration, PaymentRequestDto paymentRequestDto) throws JsonProcessingException {
        String path = "/permata-virtual-account/v2/payment-code";
        return requestVaClient.requestVa(setupConfiguration,paymentRequestDto,path);
    }
}
