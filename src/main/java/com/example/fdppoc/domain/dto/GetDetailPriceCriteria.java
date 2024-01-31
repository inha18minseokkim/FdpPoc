package com.example.fdppoc.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Builder
@Data
public class GetDetailPriceCriteria {
    private String baseDate;
    private Long regionGroupId;
    private Long innerProductId;
    private String customerId;
}
