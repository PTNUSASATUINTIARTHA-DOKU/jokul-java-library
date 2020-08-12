package com.doku.java.library.service.va;

import com.doku.java.library.builder.ClientBuilder;
import com.doku.java.library.client.va.MandiriClient;
import com.doku.java.library.client.va.MandiriSyariahClient;
import com.doku.java.library.dto.va.payment.request.PaymentCodeRequestDto;
import com.doku.java.library.dto.va.payment.response.PaymentCodeResponseDto;
import com.doku.java.library.pojo.SetupConfiguration;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class VaServices {

    public PaymentCodeResponseDto generateMandiriVa(SetupConfiguration setupConfiguration, PaymentCodeRequestDto paymentCodeRequestDto) throws IOException {
       MandiriClient mandiriClient = new ClientBuilder().build(setupConfiguration.getEnvironment(), MandiriClient.class);
       return mandiriClient.generateMandiriVa(paymentCodeRequestDto).execute().body();
    }

    public PaymentCodeResponseDto generateMandiriSyariahVa(SetupConfiguration setupConfiguration, PaymentCodeRequestDto paymentCodeRequestDto) throws IOException {
        MandiriSyariahClient mandiriSyariahClient = new ClientBuilder().build(setupConfiguration.getEnvironment(), MandiriSyariahClient.class);
        return mandiriSyariahClient.generateMandiriSyariahVa(paymentCodeRequestDto).execute().body();
    }

}
