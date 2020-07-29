package com.doku.sdk.builder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import okhttp3.*;
import okio.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ClientBuilder {

    public <T> T build(String baseUrl, Class<T> clasz) {
        Logger logger = LoggerFactory.getLogger(clasz);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(chain -> {

                    Request request = chain.request();
                    logger.info("--- REQUEST ---");
                    logger.info("Request URI      : {}", request.url());
                    logger.info("Request Method   : {}", request.method());
                    logger.info("Request Header   : {}", request.body().contentType());
                    logger.info("Request Body     : {}", bodyToStringRequest(request));


                    Response response = chain.proceed(request);

                    logger.info("--- RESPONSE ---");
                    logger.info("Response Code    : {}", response.code());
                    logger.info("Response Message : {}", response.message());

                    ResponseBody body = response.body();
                    String bodyString = body.string();
                    MediaType contentType = body.contentType();

                    logger.info("Response Message : {}", bodyString);

                    return response.newBuilder().body(ResponseBody.create(contentType,bodyString)).build();
                })
                .build();

        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create(objectMapperConfig()))
                .baseUrl(baseUrl)
                .build()
                .create(clasz);
    }

    private static String bodyToStringRequest(final Request request) throws IOException {
        final Request copy = request.newBuilder().build();
        final Buffer buffer = new Buffer();
        copy.body().writeTo(buffer);
        return buffer.readUtf8();
    }

    public ObjectMapper objectMapperConfig() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }
}
