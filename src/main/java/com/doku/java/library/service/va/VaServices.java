package com.doku.java.library.service.va;

import com.doku.java.library.client.va.*;
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
    public PaymentResponseDto generateBcaVa(SetupConfiguration setupConfiguration, PaymentRequestDto paymentRequestDto) throws IOException {
        ResponseEntity<PaymentResponseDto> bcaVA = new BcaVaClient().generateBcaVa(setupConfiguration, paymentRequestDto);
        return bcaVA.getBody();
    }
    public PaymentResponseDto generateMandiriVa(SetupConfiguration setupConfiguration, PaymentRequestDto paymentRequestDto) throws IOException {
        ResponseEntity<PaymentResponseDto> mandiriVA = new MandiriVaClient().generateMandiriVa(setupConfiguration, paymentRequestDto);
        return mandiriVA.getBody();
    }
    public PaymentResponseDto generateBsmVa(SetupConfiguration setupConfiguration, PaymentRequestDto paymentRequestDto) throws IOException {
        ResponseEntity<PaymentResponseDto> bsmVA = new BsmVaClient().generateBsmVa(setupConfiguration, paymentRequestDto);
        return bsmVA.getBody();
    }
    public PaymentResponseDto generatePermataVa(SetupConfiguration setupConfiguration, PaymentRequestDto paymentRequestDto) throws IOException {
        ResponseEntity<PaymentResponseDto> bsmVA = new PermataVaClient().generatePrmataVa(setupConfiguration, paymentRequestDto);
        return bsmVA.getBody();
    }
}
