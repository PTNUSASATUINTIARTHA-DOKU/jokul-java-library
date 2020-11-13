# Jokul Java Library

Official Java Library for Jokul API. Visit [https://jokul.doku.com](https://jokul.doku.com) for more information about the product and [https://jokul.doku.com/docs](https://jokul.doku.com/docs) for the technical documentation.

## Table of Contents

- [Payment Channels Supported](#payment-channels-supported)
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
  - [Setup Configuration](#setup-configuration)
  - [Virtual Account](#virtual-account)
  - [Credit Card](#credit-card)
  - [Handling HTTP Notification](#handling-http-notification)
- [Sample Project](#sample-project)
- [Help and Support](#help-and-support)

## Payment Channels Supported

- DOKU VA

## Requirements

Java 1.8 or above

## Installation

### Maven

Put the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.doku</groupId>
    <artifactId>java-library</artifactId>
    <version>2.0.0</version>
</dependency>
```

### SpringBoot Configuration

If you use Spring Boot, you migh want to add these package into your main Apps:

`@SpringBootApplication(scanBasePackages = {"your-project-package","com.doku.java.library"})`

## Usage

### Setup Configuration

Get your Client ID and Shared Key from Jokul Back Office. [Sandbox Jokul Back Office (for testing purpose)](https://sandbox.doku.com/bo/login) / [Production Jokul Back Office (for real payments)](https://jokul.doku.com/bo/login)

Setup your configuration:

```java
import com.doku.java.library.pojo.SetupConfiguration;

SetupConfiguration setupConfiguration = SetupConfiguration.builder()
        .clientId("YOUR_CLIENT_ID")
        .key("YOUR_SHARED_KEY")
        .environment("production")
        .setupServerLocation()
        .build();

```

If you want to hit to Sandbox, change to `.environment("sandbox")`

### Virtual Account

First prepare your payment request data:

```java
import com.doku.java.library.dto.va.payment.request.CustomerRequestDto;
import com.doku.java.library.dto.va.payment.request.OrderRequestDto;
import com.doku.java.library.dto.va.payment.request.PaymentRequestDto;
import com.doku.java.library.dto.va.payment.request.VirtualAccountInfoRequestDto;


 PaymentRequestDto.builder()
        .customer(CustomerRequestDto.builder()
                .email(paymentCodeInboundDto.getEmail())
                .name(paymentCodeInboundDto.getCustomerName())
                .build())
        .order(OrderRequestDto.builder()
                .invoiceNumber("YOUR_INVOICE_NUMBER")
                .amount(100000)
                .build())
        .virtualAccountInfo(VirtualAccountInfoRequestDto.builder()
                .expiredTime(60)
                .reusableStatus(false)
                .info1("FREE TEXT 1")
                .info2("FREE TEXT 2")
                .info3("FREE TEXT 3")
                .build())
        .setAdditionalInfo("key Object", Object)
        .build();
```

For further details of each parameter, please refer to our [Jokul Docs](https://jokul.doku.com/docs/docs/jokul-direct/virtual-account/virtual-account-overview).

#### DOKU VA

After preparing your request data, you can now generate the payment code / virtual account number:

```java
import com.doku.java.library.service.va.*;
import com.doku.java.library.dto.va.payment.response.*;
 
PaymentResponseDto paymentResponseDto =new VaServices().generateDokuVa(setupConfiguration, paymentRequestDto);
```

#### Example Code - Virtual Account

Putting them all together. Here is the example code from setup your configuration to generate payment code / virtual account number:

```java
import com.doku.java.library.dto.va.payment.request.*;
import com.doku.java.library.dto.va.payment.response.*;
import com.doku.java.library.pojo.SetupConfiguration;
 
SetupConfiguration setupConfiguration = SetupConfiguration.builder()
        .clientId("YOUR_CLIENT_ID")
        .key("YOUR_SHARED_KEY")
        .environment("production")
        .setupServerLocation()
        .build();
 
PaymentRequestDto paymentCodeRequestDto = PaymentRequestDto.builder()
        .customer(CustomerRequestDto.builder()
                .email(paymentCodeInboundDto.getEmail())
                .name(paymentCodeInboundDto.getCustomerName())
                .build())
        .order(OrderRequestDto.builder()
                .invoiceNumber("YOUR_INVOICE_NUMBER")
                .amount(100000)
                .build())
        .virtualAccountInfo(VirtualAccountInfoRequestDto.builder()
                .expiredTime(60)
                .reusableStatus(false)
                .info1("FREE TEXT 1")
                .info2("FREE TEXT 2")
                .info3("FREE TEXT 3")
                .build())
        .setAdditionalInfo()
        .build();
 
PaymentResponseDto paymentResponseDto = new VaServices().generateDokuVa(setupConfiguration, paymentRequestDto);
```

### Credit Card

Prepare your request data:

```java
import com.doku.java.library.dto.cc.request.*;
import com.doku.java.library.dto.cc.response.*;
import com.doku.java.library.pojo.SetupConfiguration;

List<LineItemRequestDto> lineItemRequestDtoList = new ArrayList<>();
        lineItemRequestDtoList.add(LineItemRequestDto.builder()
                .name("") // Insert Product Name of Merchant
                .price(0L) // Insert Product Price of Merchant
                .quantity(0) // Insert Product Quanity of Merchant
                .build());


      SetupConfiguration setupConfigurationLibrary = SetupConfiguration
                        .builder()
                        .clientId(setupConfigurationEntity.getClientId())
                        .key(setupConfigurationEntity.getkey())
                        .environment("http://app-sit.doku.com/")
                        .setupServerLocation()
                        .build();
                        
        PaymentTokenRequestDto paymentRequestDto = PaymentTokenRequestDto.builder()
                .client(ClientRequestDto.builder()
                        .id("CLIENT-ID")
                        .build())
                .customer(CustomerRequestDto.builder()
                        .id("CUSTOMER-ID")
                        .name("CUSTOMER-NAME")
                        .address("CUSTOMER-ADDRESS")
                        .country("CUSTOMER-COUNTRY")
                        .email("CUSTOMER-EMAIL")
                        .phone("CUSTOMER-PHONE")
                        .build())
                .order(OrderRequestDto.builder()
                        .amount(0L) //TOTAL AMOUNT OF ORDER
                        .invoiceNumber("ORDER-INVOICE-NUMBER")
                        .callbackUrl("CALLBACK-URL") // Redirect Url for notify merchant payment success or failed
                        .lineItems(lineItemRequestDtoList)
                        .createdDate("CREATED-DATE EPOCH TIME FORMAT") // 
                        .sessionId("SESSION-ID")
                        .build())
                .key("1")
                .build();
```

##### Request Payment Token

After preparing your request data, you can now generate the payment token:

```java
import com.doku.java.library.service.cc;
PaymentTokenResponseDto actual = ccService.generateToken(setupConfiguration, paymentRequestDto);

```

#### Example Code - Credit Card

```java
import com.doku.java.library.dto.cc.request.*;
import com.doku.java.library.dto.cc.response.*;
import com.doku.java.library.pojo.SetupConfiguration;


List<LineItemRequestDto> lineItemRequestDtoList = new ArrayList<>();
        lineItemRequestDtoList.add(LineItemRequestDto.builder()
                .name("Granola Kering")
                .price(10000L)
                .quantity(1)
                .build());


        PaymentTokenRequestDto paymentRequestDto = PaymentTokenRequestDto.builder()
                .client(ClientRequestDto.builder()
                        .id("CLIENTID")
                        .build())
                .customer(CustomerRequestDto.builder()
                        .id("CUSTOMERID")
                        .name("CUSTOMERNAME")
                        .address("CUSTOMERADDRESS")
                        .country("ID")
                        .email("CUSTOMEREMAIL)
                        .phone("PHONENUMBER")
                        .build())
                .order(OrderRequestDto.builder()
                        .amount(1000L)
                        .invoiceNumber("INV-NUM")
                        .callbackUrl("http://callbackurl.com")
                        .lineItems(lineItemRequestDtoList)
                        .createdDate("EPOCHTIME")
                        .sessionId("SESSIONID")
                        .build())
                .key("1")
                .build();
        PaymentTokenResponseDto actual = ccService.generateToken(setupConfiguration, paymentRequestDto);
```

### Handling HTTP Notification

For async payment from these channels:

- Virtual Account

We will send the HTTP Notification after the payment made from your customers. Therefore, you will need to handle the notification to update the transaction status on your end. Here is the steps:

1. Create notification URL (endpoint) on your server to receieve HTTP POST notification from Jokul. The notification will be sent to you whenever the transaction status is updated on Jokul side. The sample code available in [Jokul Java Example](https://github.com/PTNUSASATUINTIARTHA-DOKU/jokul-java-example).
1. Setup the notification URL that you made  to the Payment Configuration on Jokul Back Office. [Sandbox Jokul Back Office (for testing purpose)](https://sandbox.doku.com/bo/login) / [Production Jokul Back Office (for real payments)](https://jokul.doku.com/bo/login)
1. Test the payment with our [Payment Simulator](https://sandbox.doku.com/integration/simulator) (for testing purpose)

```java
@PostMapping(value = "/notify", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NotifyResponseBody> getData(@RequestHeader(value = "Client-Id") String clientId,
                                                     @RequestHeader(value = "Signature") String signature,
                                                     @RequestHeader(value = "Request-Id") String requestId,
                                                     @RequestHeader(value = "Request-Timestamp") String requestTimeStamp,
                                                     @RequestBody String bodyRequest) throws  NoSuchAlgorithmException, InvalidKeyException {
        
        NotifyResponseBody notifyResponseBody = null;
        // Verify the notification authenticity
        if (notifyRequestHeader.getSignature().equals(generateSignature(notifyRequestHeader,rawBody))) {
             notifyResponseBody = NotifyResponseBody.builder().order(
                    OrderResponseDto.builder().
                            amount(notifyRequestBody.getOrder().getAmount()).
                            invoiceNumber(notifyRequestBody.getOrder().getInvoiceNumber())
                            .build())
                    .virtualAccountInfo(
                            VirtualAccountInfoResponseDto.builder().
                                    virtualAccountNumber(notifyRequestBody.getVirtualAccountInfo().getVirtualAccountNumber())
                                    .build()).build();

            // TODO update your transaction status on your end
            }

        return new ResponseEntity<>(notifResponse, HttpStatus.OK);
    }
```

We encourage you to verify the notification authenticity whether it is coming from DOKU or not. You can use these sample code:

```java
private String generateSignature(NotifyRequestHeader notifyRequestHeader,String rawBody) throws NoSuchAlgorithmException, InvalidKeyException {

    SignatureComponentDTO signatureComponentDTO = SignatureComponentDTO.builder()
            .clientId(notifyRequestHeader.getClientId())
            .requestId(notifyRequestHeader.getRequestId())
            .timestamp(notifyRequestHeader.getRequestTimeStamp())
            .requestTarget("/notify")
            .secretKey("YOUR_SHARED_KEY")
            .messageBody(rawBody)
            .build();

    GenerateSignature generateSignature = new GenerateSignature();
    String signatureGenerated = generateSignature.createSignatureRequest(signatureComponentDTO);
    log.info("Signature generated from Jokul: "+notifyRequestHeader.getSignature());
    log.info("Signature generated from your side: "+signatureGenerated);

    return  signatureGenerated;
}
```

For further reference, please refer to our [Jokul Docs](https://jokul.doku.com/docs).

## Sample Project

Please refer to this repo for the example project: [Jokul Java Example](https://github.com/PTNUSASATUINTIARTHA-DOKU/jokul-java-example).

## Help and Support

Got any issues? Found a bug? Have a feature requests? You can [open new issue](https://github.com/PTNUSASATUINTIARTHA-DOKU/jokul-java-library/issues/new).

For further information, you can contact us on [care@doku.com](mailto:care@doku.com).
