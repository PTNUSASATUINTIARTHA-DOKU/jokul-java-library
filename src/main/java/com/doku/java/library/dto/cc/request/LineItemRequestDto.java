
package com.doku.java.library.dto.cc.request;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class LineItemRequestDto {
    private String name;
    private Integer quantity;
    private Long price;
}
