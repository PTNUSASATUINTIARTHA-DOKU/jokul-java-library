package com.doku.java.library.client.va;

import com.doku.java.library.dto.va.payment.request.PaymentCodeRequestDto;
import com.doku.java.library.dto.va.payment.response.PaymentCodeResponseDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MandiriClient {
    @POST("mandiri-virtual-account/v1/payment-code")
    Call<PaymentCodeResponseDto> generateMandiriVa(@Body PaymentCodeRequestDto paymentCodeRequestDto);
}
