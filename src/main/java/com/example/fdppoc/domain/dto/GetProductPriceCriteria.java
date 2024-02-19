package com.example.fdppoc.domain.dto;

import com.example.fdppoc.code.BaseRange;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class GetProductPriceCriteria {
    private String baseDate;
    private String targetInnerProductId;
    private BaseRange rangeForLength;
    private BaseRange rangeForTag;
    private String regionGroupId;
    private String customerId;
}
