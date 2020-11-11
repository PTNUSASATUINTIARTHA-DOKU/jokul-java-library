package com.doku.java.library.service.va;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Signature component dto.
 */
@Builder
@Getter
@Setter
public class SignatureComponentDTO {

    private String httpMethod;
    private String clientId;
    private String requestId;
    private String requestTarget;
    private String timestamp;
    private String messageBody;
    private String secretKey;

}
