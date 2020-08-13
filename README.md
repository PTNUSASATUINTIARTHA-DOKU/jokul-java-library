# Jokul Java Library

Official Java Library for Jokul API. Visit [https://jokul.doku.com](https://jokul.doku.com) for more information about the product and [https://jokul.doku.com/docs](https://jokul.doku.com/docs) for the technical documentation.

## Table of Contents

- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
  - [Setup Configuration](#setup-configuration)
  - [Virtual Account](#virtual-account)
- [Example](#example)
- [Help and Support](#help-and-support)

## Requirements

Java 1.8 or above

## Installation

### Maven

Put the following dependency to your `pom.xml`:

```java
<dependency>
    <groupId>com.doku</groupId>
    <artifactId>java-library</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Usage

### Setup Configuration

Get your Client ID and Shared Key from [Jokul Back Office](https://jokul.doku.com/bo/login).

Setup your configuration:

```java

import com.doku.java.library.pojo.SetupConfiguration;

SetupConfiguration setupConfiguration = SetupConfiguration.builder()
        .clientId("YOUR_CLIENT_ID")
        .merchantName("YOUR BUSINESS NAME")
        .sharedKey("YOUR_SHARED_KEY")
        .environment(baseUrl)
        .setupServerLocation()
        .build();
```
#### Server Location
Sandbox: `"sandbox"`
Production: `"production"`

### Virtual Account
Prepare your request data:

```java
import com.doku.java.library.dto.va.payment.request.*;

PaymentCodeRequestDto.builder()
        .client(ClientRequestDto.builder()
                .id("YOUR_CLIENT_ID")
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
        .sharedKey(sharedKey)
        .customer(CustomerRequestDto.builder()
                .name("YOUR CUSTOMER NAME")
                .email("YOUR CUSTOMER EMAIL")
                .build())
        .generateWords()
        .build();
```

#### Mandiri VA

After preparing your request data, you can now generate the payment code / virtual account number:

```java
import com.doku.java.library.service.va;
 
PaymentCodeResponseDto paymentCodeResponseDto =new GeneratePaycodeServices().generateMandiriVa(setupConfiguration, paymentCodeRequestDto);
```

#### Mandiri Syariah VA

After preparing your request data, you can now generate the payment code / virtual account number:

```java
import com.doku.java.library.service.va;
 
PaymentCodeResponseDto paymentCodeResponseDto = new GeneratePaycodeServices().generateMandiriSyariahVa(setupConfiguration, paymentCodeRequestDto);
```

#### Example Code - Virtual Account

Putting them all together. Here is the example code from setup your configuration to generate payment code / virtual account number:

```java

import com.doku.java.library.dto.va.payment.request.*;
import com.doku.java.library.dto.va.payment.response.*;
import com.doku.java.library.pojo.SetupConfiguration;
 
SetupConfiguration setupConfiguration = SetupConfiguration.builder()
        .clientId("YOUR_CLIENT_ID")
        .merchantName("YOUR BUSINESS NAME")
        .sharedKey("YOUR_SHARED_KEY")
        .serverLocation(ServerLocation.SANDBOX.getUrl())
        .build();
 
PaymentCodeRequestDto.builder()
        .client(ClientRequestDto.builder()
                .id("YOUR_CLIENT_ID")
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
        .sharedKey(sharedKey)
        .customer(CustomerRequestDto.builder()
                .name("YOUR CUSTOMER NAME")
                .email("YOUR CUSTOMER EMAIL")
                .build())
        .generateWords()
        .build();
 
PaymentCodeResponseDto paymentCodeResponseDto = new GeneratePaycodeServices().generateMandiri(setupConfiguration, paymentCodeRequestDto);
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


        com.doku.java.library.pojo.SetupConfiguration setupConfigurationLibrary = com.doku.java.library.pojo.SetupConfiguration
                        .builder()
                        .clientId(setupConfigurationEntity.getClientId())
                        .merchantName(setupConfigurationEntity.getMerchantName())
                        .sharedKey(setupConfigurationEntity.getSharedKey())
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
                .sharedKey("1")
                .build();
```

##### Request Payment Token

After preparing your request data, you can now generate the payment token :

```java
import com.doku.java.library.service.cc;
PaymentTokenResponseDto actual = ccService.generateToken(setupConfiguration, paymentRequestDto);

```

#### Example Code - Virtual Account

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
                .sharedKey("1")
                .build();


        PaymentTokenResponseDto actual = ccService.generateToken(setupConfiguration, paymentRequestDto);

```

## Example

Please refer to this repo for the example project: [Jokul Java Example](https://github.com/PTNUSASATUINTIARTHA-DOKU/jokul-java-example).

## Help and Support

Got any issues? Found a bug? Have a feature requests? You can [open new issue](https://github.com/PTNUSASATUINTIARTHA-DOKU/jokul-java-library/issues/new).

For further information, you can contact us on [care@doku.com](mailto:care@doku.com).
