package com.doku.java.library.service.cc;

import com.doku.java.library.builder.ClientBuilder;
import com.doku.java.library.client.cc.CcClient;
import com.doku.java.library.dto.cc.request.PaymentTokenRequestDto;
import com.doku.java.library.dto.cc.response.PaymentTokenResponseDto;
import com.doku.java.library.pojo.SetupConfiguration;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CCService {
    public PaymentTokenResponseDto generateToken(SetupConfiguration setupConfiguration, PaymentTokenRequestDto paymentRequestDto) throws IOException {
        CcClient ccClient = new ClientBuilder().build(setupConfiguration.getEnvironment(), CcClient.class);
        return ccClient.ccPaymentProcess(paymentRequestDto).execute().body();
    }
}
