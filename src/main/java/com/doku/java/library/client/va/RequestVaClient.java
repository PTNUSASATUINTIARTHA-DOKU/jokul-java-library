package com.doku.java.library.client.va;

import com.doku.java.library.builder.ClientSetup;
import com.doku.java.library.dto.va.payment.request.PaymentRequestDto;
import com.doku.java.library.dto.va.payment.response.PaymentResponseDto;
import com.doku.java.library.pojo.SetupConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class RequestVaClient {
    public ResponseEntity<PaymentResponseDto> requestVa(SetupConfiguration setupConfiguration, PaymentRequestDto paymentRequestDto,String path) throws JsonProcessingException  {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        HttpEntity<String> setup = new ClientSetup().setupRequest(setupConfiguration, paymentRequestDto, path);
        log.info("===REQUEST====");
        log.info("REQUEST URL : "+ setupConfiguration.getServerLocation()+path);
        log.info("REQUEST HEADER : "+ gson.toJson(setup.getHeaders()));
        log.info("REQUEST BODY : "+ gson.toJson(setup.getBody()));

        ResponseEntity<PaymentResponseDto> responseDtoResponseEntity = new RestTemplate().postForEntity(setupConfiguration.getServerLocation() + path, setup, PaymentResponseDto.class);

        log.debug("===RESPONSE====");
        log.debug("RESPONSE BODY : "+ gson.toJson(responseDtoResponseEntity.getBody()));

        return responseDtoResponseEntity;
    }
}
