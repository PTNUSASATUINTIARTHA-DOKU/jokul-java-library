package com.doku.sdk.client;

import com.doku.sdk.dto.payment.request.PaymentCodeRequestDto;
import com.doku.sdk.dto.payment.response.PaymentCodeResponseDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MandiriClient {
    @POST("mandiri-virtual-account/v1/payment-code")
    Call<PaymentCodeResponseDto> mandiriGeneratePaymentCode(@Body PaymentCodeRequestDto paymentCodeRequestDto);
}
