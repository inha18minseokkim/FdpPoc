package com.example.fdppoc.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class SearchInnerProductsRequest {
    private String baseDate;
    private String customerId;
}
