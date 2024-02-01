package com.example.fdppoc.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Builder
@Data
public class GetDetailPriceCriteria {
    private String baseDate;
    private String regionGroupId;
    private String innerProductId;
    private String customerId;
}
