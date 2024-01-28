package com.example.fdppoc.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;


@ToString
@Data
@Builder
public class LegacyAllInnerProductsRequest {
    private String customerId;
    private Long regionGroupId;
    private String baseDate;
}
