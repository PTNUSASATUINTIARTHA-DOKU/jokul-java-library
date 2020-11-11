package com.doku.java.library.client.cc;

import com.doku.java.library.dto.cc.request.PaymentTokenRequestDto;
import com.doku.java.library.dto.cc.response.PaymentTokenResponseDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CcClient {
    @POST("wt-redirect-services/token")
    Call<PaymentTokenResponseDto> ccPaymentProcess(@Body PaymentTokenRequestDto paymentTokenRequestDto);
}
