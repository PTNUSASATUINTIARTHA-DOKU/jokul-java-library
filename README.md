# Jokul Java Library

Official Java Library for Jokul API. Visit [https://jokul.doku.com](https://jokul.doku.com) for more information about the product and [https://jokul.doku.com/docs](https://jokul.doku.com/docs) for the technical documentation.

## Table of Contents

- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
  - [Setup Configuration](#setup-configuration)
  - [Virtual Account](#virtual-account)
  - [Credit Card](#credit-card)
- [Example](#example)
- [Help and Support](#help-and-support)

## Requirements

Java 1.8 or above

## Installation

### Maven

Put the following dependency to your `pom.xml`:

```
<dependency>
    <groupId>com.doku</groupId>
    <artifactId>java-library</artifactId>
    <version>2.0.0</version>
</dependency>
```
### SpringBoot Configuration
Add package into your main Apps
`@SpringBootApplication(scanBasePackages = {"your-project-package","com.doku.java.library"})`
## Usage

### Setup Configuration

Get your Client ID and Shared Key from [Jokul Back Office](https://jokul.doku.com/bo/login).

Setup your configuration:

```

import com.doku.java.library.pojo.SetupConfiguration;

SetupConfiguration setupConfiguration = SetupConfiguration.builder()
        .clientId("YOUR_CLIENT_ID")
        .key("YOUR_SHARED_KEY")
        .environment("sandbox")
        .setupServerLocation()
        .build();

```
#### Server Location
Sandbox: `"sandbox"`
Production: `"production"`

### Virtual Account
Prepare your request data:

```
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

#### DOKU VA

After preparing your request data, you can now generate the payment code / virtual account number:

```
import com.doku.java.library.service.va.*;
import com.doku.java.library.dto.va.payment.response.*;
 
PaymentResponseDto paymentResponseDto =new VaServices().generateDokuVa(setupConfiguration, paymentRequestDto);
```

#### Example Code - Virtual Account

Putting them all together. Here is the example code from setup your configuration to generate payment code / virtual account number:

```
import com.doku.java.library.dto.va.payment.request.*;
import com.doku.java.library.dto.va.payment.response.*;
import com.doku.java.library.pojo.SetupConfiguration;
 
SetupConfiguration setupConfiguration = SetupConfiguration.builder()
        .clientId("YOUR_CLIENT_ID")
        .key("YOUR_SHARED_KEY")
        .environment("sandbox")
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
        .setAdditionalInfo("key Object", Object)
        .build();
 
PaymentResponseDto paymentResponseDto = new VaServices().generateMandiriVa(setupConfiguration, paymentRequestDto);
```
Notify Payment Virtual Account you can use object in this library "NotifyRequestBody".
To validate signature you can use class GenerateSignature.createSignatureRequest() to create signature
```
....
SignatureComponentDTO signatureComponentDTO = SignatureComponentDTO.builder()
                .clientId(clientId)
                .requestId(requestId)
                .timestamp(requestTimeStamp)
                .requestTarget("/demo/java-library/demo/java-library/notify")
                .secretKey("SK-hCJ42G28TA0MKG9LE2E_1")
                .messageBody(bodyRequest)
                .build();

        GenerateSignature generateSignature = new GenerateSignature();
        String signatureGenerated = generateSignature.createSignatureRequest(signatureComponentDTO);

//Your Logic Here 
```
### Credit Card
Prepare your request data:

```
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

After preparing your request data, you can now generate the payment token :

```
import com.doku.java.library.service.cc;
PaymentTokenResponseDto actual = ccService.generateToken(setupConfiguration, paymentRequestDto);

```

#### Example Code - Credit Card

```
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

## Example

Please refer to this repo for the example project: [Jokul Java Example](https://github.com/PTNUSASATUINTIARTHA-DOKU/jokul-java-example).

## Help and Support

Got any issues? Found a bug? Have a feature requests? You can [open new issue](https://github.com/PTNUSASATUINTIARTHA-DOKU/jokul-java-library/issues/new).

For further information, you can contact us on [care@doku.com](mailto:care@doku.com).
