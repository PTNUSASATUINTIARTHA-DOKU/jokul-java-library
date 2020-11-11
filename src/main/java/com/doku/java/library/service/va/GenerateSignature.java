package com.doku.java.library.service.va;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
public class GenerateSignature {
    public String createSignatureRequest(SignatureComponentDTO componentDTO) throws NoSuchAlgorithmException, InvalidKeyException {
        // build component signature
        StringBuilder component = new StringBuilder();
        component.append(Constant.CLIENT_ID).append(Constant.COLON_SYMBOL).append(componentDTO.getClientId());
        component.append(Constant.NEW_LINE);
        component.append(Constant.REQUEST_ID).append(Constant.COLON_SYMBOL).append(componentDTO.getRequestId());
        component.append(Constant.NEW_LINE);
        component.append(Constant.REQUEST_TIMESTAMP).append(Constant.COLON_SYMBOL).append(componentDTO.getTimestamp());
        component.append(Constant.NEW_LINE);
        component.append(Constant.REQUEST_TARGET).append(Constant.COLON_SYMBOL).append(componentDTO.getRequestTarget());
        String digest = HashTool.sha256Base64(componentDTO.getMessageBody());
        component.append(Constant.NEW_LINE);
        component.append(Constant.DIGEST).append(Constant.COLON_SYMBOL).append(digest);

        log.info("Expected Component Signature : \n" +
                "{} ", component.toString());

        String hashResult = HashTool.hmacSHA256(component.toString(), componentDTO.getSecretKey());
        StringBuilder signature = new StringBuilder();
        signature.append("HMACSHA256");
        signature.append(Constant.EQUALS_SIGN_SYMBOL);
        signature.append(hashResult);
        log.info("nilai signature : "+signature.toString());
        return signature.toString();
    }
}
