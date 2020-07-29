package com.doku.sdk.service;

import com.doku.sdk.builder.ClientBuilder;
import com.doku.sdk.client.MandiriClient;
import com.doku.sdk.client.MandiriSyariahClient;
import com.doku.sdk.dto.payment.request.PaymentCodeRequestDto;
import com.doku.sdk.dto.payment.response.PaymentCodeResponseDto;
import com.doku.sdk.pojo.SetupConfiguration;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class GeneratePaycodeServices {

    public PaymentCodeResponseDto generateMandiriPaymentCode(SetupConfiguration setupConfiguration, PaymentCodeRequestDto paymentCodeRequestDto) throws IOException {
       MandiriClient mandiriClient = new ClientBuilder().build(setupConfiguration.getServerLocation(), MandiriClient.class);
       return mandiriClient.mandiriGeneratePaymentCode(paymentCodeRequestDto).execute().body();
    }

    public PaymentCodeResponseDto generateMandiriSyariahPaymentCode(SetupConfiguration setupConfiguration, PaymentCodeRequestDto paymentCodeRequestDto) throws IOException {
        MandiriSyariahClient mandiriSyariahClient = new ClientBuilder().build(setupConfiguration.getServerLocation(), MandiriSyariahClient.class);
        return mandiriSyariahClient.mandiriSyariahGeneratePaymentCode(paymentCodeRequestDto).execute().body();
    }

}
