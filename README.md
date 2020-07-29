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
import com.doku.sdk.pojo.SetupConfiguration;
import com.doku.sdk.pojo.ServerLocation;
 
SetupConfiguration setupConfiguration = SetupConfiguration.builder()
        .clientId("YOUR_CLIENT_ID")
        .merchantName("YOUR BUSINESS NAME")
        .sharedKey("YOUR_SHARED_KEY")
        .serverLocation(ServerLocation.SANDBOX.getUrl())
        .build();
```

Sandbox: `ServerLocation.SANDBOX.getUrl()`

Production: `ServerLocation.PROD.getUrl()`

### Virtual Account

Prepare your request data:

```java
import com.doku.sdk.dto.payment.request.*;
 
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
import com.doku.sdk.service;
import com.doku.sdk.dto.payment.response.*;
 
PaymentCodeResponseDto paymentCodeResponseDto =new GeneratePaycodeServices().generateMandiri(setupConfiguration, paymentCodeRequestDto);
```

#### Mandiri Syariah VA

After preparing your request data, you can now generate the payment code / virtual account number:

```java
import com.doku.sdk.service;
import com.doku.sdk.dto.payment.response.*;
 
PaymentCodeResponseDto paymentCodeResponseDto = new GeneratePaycodeServices().generateMandiriSyariah(setupConfiguration, paymentCodeRequestDto);
```

#### Example Code - Virtual Account

Putting them all together. Here is the example code from setup your configuration to generate payment code / virtual account number:

```java
import com.doku.sdk.dto.payment.request.*;
import com.doku.sdk.dto.payment.response.*;
import com.doku.sdk.pojo.SetupConfiguration;
import com.doku.sdk.pojo.ServerLocation;
import com.doku.sdk.service;
 
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

## Example

Please refer to this repo for the example project: [Jokul Java Example](https://github.com/PTNUSASATUINTIARTHA-DOKU/jokul-java-example).

## Help and Support

Got any issues? Found a bug? Have a feature requests? You can [open new issue](https://github.com/PTNUSASATUINTIARTHA-DOKU/jokul-java-library/issues/new).

For further information, you can contact us on [care@doku.com](mailto:care@doku.com).
