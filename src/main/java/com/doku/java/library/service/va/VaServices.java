package com.doku.java.library.service.va;

import com.doku.java.library.builder.ClientBuilder;
import com.doku.java.library.client.va.MandiriClient;
import com.doku.java.library.client.va.MandiriSyariahClient;
import com.doku.java.library.dto.va.payment.request.PaymentCodeRequestDto;
import com.doku.java.library.dto.va.payment.response.ErrorResponseDto;
import com.doku.java.library.dto.va.payment.response.PaymentCodeResponseDto;
import com.doku.java.library.pojo.SetupConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;

@Service
public class VaServices {

    public PaymentCodeResponseDto generateMandiriVa(SetupConfiguration setupConfiguration, PaymentCodeRequestDto paymentCodeRequestDto) throws IOException {
        MandiriClient mandiriClient = new ClientBuilder().build(setupConfiguration.getServerLocation(), MandiriClient.class);
        return getDto(mandiriClient.generateMandiriVa(paymentCodeRequestDto).execute());
    }

    public PaymentCodeResponseDto generateMandiriSyariahVa(SetupConfiguration setupConfiguration, PaymentCodeRequestDto paymentCodeRequestDto) throws IOException {
        MandiriSyariahClient mandiriSyariahClient = new ClientBuilder().build(setupConfiguration.getEnvironment(), MandiriSyariahClient.class);
        return getDto(mandiriSyariahClient.generateMandiriSyariahVa(paymentCodeRequestDto).execute());
    }

    private PaymentCodeResponseDto getDto(Response<PaymentCodeResponseDto> paymentCodeResponseDtoResponse) throws IOException {
        if (null != paymentCodeResponseDtoResponse.body()) {
            return paymentCodeResponseDtoResponse.body();
        } else {
            ObjectMapper mapper = new ObjectMapper();
            PaymentCodeResponseDto paymentCodeResponseDto = mapper
                    .readValue(paymentCodeResponseDtoResponse.errorBody().string(), PaymentCodeResponseDto.class);
            ErrorResponseDto errorResponseDto = paymentCodeResponseDto.getError();
            errorResponseDto.setStatusCode(paymentCodeResponseDtoResponse.code());
            paymentCodeResponseDto.setError(errorResponseDto);
            return paymentCodeResponseDto;
        }

    }

}
