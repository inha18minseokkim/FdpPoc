package com.example.fdppoc.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class GetProductDetailLegacyResponseSubElement {
    private String baseDate;
    private Long price;
}
