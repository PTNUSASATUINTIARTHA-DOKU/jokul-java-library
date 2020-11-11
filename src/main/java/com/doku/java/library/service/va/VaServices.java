package com.doku.java.library.service.va;

import com.doku.java.library.client.va.DokuVaClient;
import com.doku.java.library.dto.va.payment.request.PaymentRequestDto;
import com.doku.java.library.dto.va.payment.response.PaymentResponseDto;
import com.doku.java.library.pojo.SetupConfiguration;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public class VaServices {
    public PaymentResponseDto generateDokuVa(SetupConfiguration setupConfiguration, PaymentRequestDto paymentRequestDto) throws IOException {
        ResponseEntity<PaymentResponseDto> dokuVA = new DokuVaClient().generateDokuVa(setupConfiguration, paymentRequestDto);
        return dokuVA.getBody();
    }
}
