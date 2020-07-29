package com.doku.sdk.client;

import com.doku.sdk.dto.payment.request.PaymentCodeRequestDto;
import com.doku.sdk.dto.payment.response.PaymentCodeResponseDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MandiriSyariahClient {
    @POST("bsm-virtual-account/v1/payment-code")
    Call<PaymentCodeResponseDto> mandiriSyariahGeneratePaymentCode(@Body PaymentCodeRequestDto paymentCodeDto);
}
