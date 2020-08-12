
package com.doku.java.library.dto.cc.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CustomerRequestDto {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String country;

}
