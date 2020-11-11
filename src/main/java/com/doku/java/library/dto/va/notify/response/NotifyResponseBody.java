package com.doku.java.library.dto.va.notify.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class NotifyResponseBody {

    private OrderResponseDto order;
    private VirtualAccountInfoResponseDto virtualAccountInfo;
}
