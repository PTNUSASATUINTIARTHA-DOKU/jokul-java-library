package com.doku.java.library.builder;

import com.doku.java.library.dto.va.payment.request.PaymentRequestDto;
import com.doku.java.library.pojo.SetupConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fascalsj.stringmanipulator.RandomCharacter;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
@Component
public class ClientSetup {
    public HttpEntity<String> setupRequest(SetupConfiguration setupConfiguration, PaymentRequestDto paymentRequestDto, String path) throws JsonProcessingException {
        String clientId = setupConfiguration.getClientId();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String date = inputFormat.format(new Date());

        String reqId = String.valueOf(System.currentTimeMillis());

        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        String jsonBodyString = gson.toJson(paymentRequestDto);


        MessageDigest md = DigestUtils.getDigest("SHA-256");
        md.update(jsonBodyString.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        String base64HmacSha256 = Base64.getEncoder().encodeToString(digest);

        String signatureComponents =
                "Client-Id:" + clientId + "\n"
                        + "Request-Id:" + reqId + "\n"
                        + "Request-Timestamp:" + date + "\n"
                        + "Request-Target:" + path + "\n"
                        + "Digest:" + base64HmacSha256;

        byte[] hmac = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, setupConfiguration.getKey().getBytes(StandardCharsets.UTF_8)).hmac(signatureComponents.getBytes());
        String result = Base64.getEncoder().encodeToString(hmac);

        ObjectMapper objectMapper = new ObjectMapper();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Signature", "HMACSHA256=" + result);
        httpHeaders.set("Request-Id", reqId);
        httpHeaders.set("Client-Id", clientId);
        httpHeaders.set("Request-Timestamp", date);
        httpHeaders.set("Request-Target", path);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new HttpEntity<>(objectMapper.writeValueAsString(paymentRequestDto), httpHeaders);
    }
}
