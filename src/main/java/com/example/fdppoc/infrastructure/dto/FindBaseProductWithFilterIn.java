package com.example.fdppoc.infrastructure.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class FindBaseProductWithFilterIn {
    private String categoryCode;
    private String itemCode;
}
