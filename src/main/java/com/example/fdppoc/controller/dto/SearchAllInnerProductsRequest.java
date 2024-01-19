package com.example.fdppoc.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class SearchAllInnerProductsRequest {
    private String baseDate;
    private String customerId;
}
